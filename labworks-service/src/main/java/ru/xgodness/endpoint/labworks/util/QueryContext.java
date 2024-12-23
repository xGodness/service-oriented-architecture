package ru.xgodness.endpoint.labworks.util;

import lombok.Getter;
import org.jooq.Condition;
import org.springframework.util.MultiValueMap;
import ru.xgodness.exception.ApplicationException;
import ru.xgodness.exception.UnexpectedInputFormatException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Getter
public class QueryContext {
    private final List<FilteringQueryToken> filteringTokens;
    private final List<SortingQueryToken> sortingTokens;
    private final Long limit;
    private final Long offset;
    private final List<String> errorMessages = new ArrayList<>();

    public QueryContext(MultiValueMap<String, String> queryParamsMap) {

        this.filteringTokens = parseFilteringTokens(queryParamsMap);
        this.sortingTokens = parseSortingTokens(queryParamsMap);
        this.limit = parseLimit(queryParamsMap);
        this.offset = parseOffset(queryParamsMap);

        if (!errorMessages.isEmpty()) throw new UnexpectedInputFormatException(errorMessages);
    }

    private List<FilteringQueryToken> parseFilteringTokens(MultiValueMap<String, String> queryParamsMap) {
        List<String> filteringParams = queryParamsMap.get("filter");
        if (filteringParams == null) return List.of();
        return IntStream.range(0, filteringParams.size())
                .mapToObj(i -> {
                    String param = filteringParams.get(i);
                    try {
                        return new FilteringQueryToken(param);
                    } catch (ApplicationException ex) {
                        errorMessages.addAll(
                                ex.getErrorMessages().getMessages().stream()
                                        .map(msg -> "Error in filter parameter #%d: ".formatted(i + 1) + msg).toList()
                        );
                        return null;
                    }
                }).toList();
    }

    private List<SortingQueryToken> parseSortingTokens(MultiValueMap<String, String> queryParamsMap) {
        List<String> sortingParams = queryParamsMap.get("sort");
        if (sortingParams == null) return List.of();

        Map<LabworkField, Boolean> parsedTokenMap = new HashMap<>();
        List<SortingQueryToken> parsedTokenList = new ArrayList<>();

        String param;
        for (int i = 0; i < sortingParams.size(); i++) {
            param = sortingParams.get(i);
            try {
                SortingQueryToken token = new SortingQueryToken(param);
                Boolean prevSortingOrder = parsedTokenMap.get(token.getField());
                if (prevSortingOrder != null && prevSortingOrder != token.isAscending())
                    errorMessages.add("Conflicting sorting parameters for field " + token.getField().toString().toLowerCase());
                else {
                    parsedTokenMap.put(token.getField(), token.isAscending());
                    parsedTokenList.add(token);
                }
            } catch (ApplicationException ex) {
                int finalI = i;
                errorMessages.addAll(
                        ex.getErrorMessages().getMessages().stream()
                                .map(msg -> "Error in sort parameter #%d: ".formatted(finalI + 1) + msg).toList()
                );
            }
        }

        return parsedTokenList;
    }

    private Long parseLimit(MultiValueMap<String, String> queryParamsMap) {
        List<String> limitList = queryParamsMap.get("limit");
        if (limitList == null) return null;
        try {
            long value = Long.parseLong(limitList.get(0));
            if (value <= 0) {
                errorMessages.add("Limit must be positive");
                return null;
            }
            return value;
        } catch (NumberFormatException ex) {
            errorMessages.add("Limit must be long type");
        } catch (IndexOutOfBoundsException ex) {
            errorMessages.add("Limit must not be null");
        }
        return null;
    }

    private Long parseOffset(MultiValueMap<String, String> queryParamsMap) {
        List<String> offsetList = queryParamsMap.get("offset");
        if (offsetList == null) return null;
        try {
            long value = Long.parseLong(offsetList.get(0));
            if (value < 0) {
                errorMessages.add("Offset must be positive or 0");
                return null;
            }
            return value;
        } catch (NumberFormatException ex) {
            errorMessages.add("Offset must be long type");
        } catch (IndexOutOfBoundsException ex) {
            errorMessages.add("Offset must not be null");
        }
        return null;
    }

    public List<Condition> mapFilteringTokensToConditions() {
        return filteringTokens.stream().map(FilteringQueryToken::mapToCondition).toList();
    }
}

