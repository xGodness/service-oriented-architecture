package ru.xgodness.model.dto.util;

import jakarta.json.bind.serializer.DeserializationContext;
import jakarta.json.bind.serializer.JsonbDeserializer;
import jakarta.json.stream.JsonParser;
import jakarta.ws.rs.ext.Provider;
import ru.xgodness.endpoint.labworks.model.dto.Difficulty;

import java.lang.reflect.Type;

@Provider
public class DifficultyDeserializer implements JsonbDeserializer<Difficulty> {
    @Override
    public Difficulty deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Type type) {
        return Difficulty.fromString(
                jsonParser.getString().toUpperCase()
        );
    }
}
