package ru.xgodness.model.dto.util;

import jakarta.json.bind.serializer.JsonbSerializer;
import jakarta.json.bind.serializer.SerializationContext;
import jakarta.json.stream.JsonGenerator;
import jakarta.ws.rs.ext.Provider;
import ru.xgodness.endpoint.minimalpoints.model.dto.MinimalPointsSum;

@Provider
public class MinimalPointsSumSerializer implements JsonbSerializer<MinimalPointsSum> {
    @Override
    public void serialize(MinimalPointsSum minimalPointsSum, JsonGenerator jsonGenerator, SerializationContext serializationContext) {
        Double value = minimalPointsSum.getValue();
        jsonGenerator.writeStartObject();
        if (value != null) {
            if (Double.isInfinite(value)) jsonGenerator.write("value", "Infinity");
            else jsonGenerator.write("value", value);
        }
        jsonGenerator.writeEnd();
    }
}
