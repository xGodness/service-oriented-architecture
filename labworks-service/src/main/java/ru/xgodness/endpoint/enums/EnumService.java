package ru.xgodness.endpoint.enums;

import lombok.extern.java.Log;
import ru.xgodness.endpoint.enums.model.dto.EnumRepresentation;
import ru.xgodness.exception.NotFoundException;
import ru.xgodness.model.generated.enums.DifficultyT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Log
public class EnumService {
    private static final Map<String, List<EnumRepresentation>> registeredEnums = new HashMap<>();

    static {
        register(DifficultyT.class, "difficulty");
    }

    private static void register(Class<?> enumC, String enumName) {
        if (!enumC.isEnum()) {
            log.warning("Could not register class %s: not a enum".formatted(enumC.getName()));
            return;
        }
        Object[] constants = enumC.getEnumConstants();
        List<EnumRepresentation> representations = IntStream.range(0, constants.length)
                .mapToObj(i -> {
                    String value = constants[i].toString().toLowerCase();
                    String[] words = value.split("_");
                    StringBuilder displayValueBuilder = new StringBuilder(
                            words[0].substring(0, 1).toUpperCase() + words[0].substring(1)
                    );
                    for (var j = 1; j < words.length; j++) displayValueBuilder.append(" ").append(words[j]);
                    return new EnumRepresentation(value, displayValueBuilder.toString(), i);
                })
                .toList();
        registeredEnums.put(enumName, representations);
    }

    public static List<EnumRepresentation> getEnumRepresentation(String enumName) {
        List<EnumRepresentation> representations = registeredEnums.get(enumName);
        if (representations == null) {
            throw new NotFoundException("Enum %s was not found".formatted(enumName));
        }
        log.info(representations.toString());
        return representations;
    }

}
