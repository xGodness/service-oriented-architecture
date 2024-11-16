package ru.xgodness.model.dto.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import ru.xgodness.endpoint.labworks.model.dto.Difficulty;

import java.io.IOException;

public class DifficultyDeserializer extends JsonDeserializer<Difficulty> {
    @Override
    public Difficulty deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return Difficulty.fromString(
                jsonParser.getValueAsString().toUpperCase()
        );
    }
}
