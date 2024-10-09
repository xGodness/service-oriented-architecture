package model.dto.util;

import jakarta.json.bind.serializer.DeserializationContext;
import jakarta.json.bind.serializer.JsonbDeserializer;
import jakarta.json.stream.JsonParser;
import jakarta.ws.rs.ext.Provider;
import model.dto.DifficultyDTO;

import java.lang.reflect.Type;

@Provider
public class DifficultyDTODeserializer implements JsonbDeserializer<DifficultyDTO> {
    @Override
    public DifficultyDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Type type) {
        return DifficultyDTO.fromString(
                jsonParser.getString().toUpperCase()
        );
    }
}
