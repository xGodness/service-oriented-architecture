package model.dto.util;

import jakarta.json.Json;
import jakarta.json.stream.JsonGenerator;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;
import model.dto.ErrorMessagesDTO;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
public class ErrorMessagesDTOMessageBodyWriter implements MessageBodyWriter<ErrorMessagesDTO> {
    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return ErrorMessagesDTO.class.isAssignableFrom((Class<?>) type);
    }

    @Override
    public void writeTo(ErrorMessagesDTO errorMessagesDTO, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {
        JsonGenerator generator = Json.createGenerator(outputStream);
        generator.writeStartObject().writeStartArray("messages");
        errorMessagesDTO.getMessages().forEach(generator::write);
        generator.writeEnd().writeEnd();
        generator.close();
    }
}
