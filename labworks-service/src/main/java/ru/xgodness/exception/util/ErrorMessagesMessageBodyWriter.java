package ru.xgodness.exception.util;

import jakarta.json.Json;
import jakarta.json.stream.JsonGenerator;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;
import ru.xgodness.exception.dto.ErrorMessages;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
public class ErrorMessagesMessageBodyWriter implements MessageBodyWriter<ErrorMessages> {
    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return ErrorMessages.class.isAssignableFrom((Class<?>) type);
    }

    @Override
    public void writeTo(ErrorMessages errorMessages, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {
        JsonGenerator generator = Json.createGenerator(outputStream);
        generator.writeStartObject().writeStartArray("messages");
        errorMessages.getMessages().forEach(generator::write);
        generator.writeEnd().writeEnd();
        generator.close();
    }
}
