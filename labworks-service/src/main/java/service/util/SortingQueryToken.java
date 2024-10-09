package service.util;

import exception.UnexpectedInputFormatException;
import lombok.Getter;
import org.jooq.SortField;

@Getter
public class SortingQueryToken extends QueryToken {
    private final boolean ascending;

    public SortingQueryToken(String queryParam) {
        String[] paramTokens = extractTokensFromParam(queryParam);
        super.setField(paramTokens[0]);
        ascending = (paramTokens.length == 1 || !paramTokens[1].equals("desc"));
    }

    private String[] extractTokensFromParam(String queryParam) {
        String[] paramTokens = queryParam.split("[\\[\\]]");
        if (paramTokens.length == 0)
            throw new UnexpectedInputFormatException("Cannot parse sort, format must be sort={parameter_name} or sort={parameter_name}[desc]");
        return paramTokens;
    }

    public SortField<?> mapToSortField() {
        return ascending ? getField().mapToJooqField().asc() : getField().mapToJooqField().desc();
    }
}
