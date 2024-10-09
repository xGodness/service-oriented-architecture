package exception;

import jakarta.ws.rs.WebApplicationException;
import lombok.Getter;
import exception.model.dto.ErrorMessagesDTO;

import java.util.List;

@Getter
public class ApplicationException extends WebApplicationException {
    private final ErrorMessagesDTO errorMessagesDTO;

    public ApplicationException(ErrorMessagesDTO errorMessagesDTO) {
        this.errorMessagesDTO = errorMessagesDTO;
    }

    public ApplicationException(String... errors) {
        this.errorMessagesDTO = new ErrorMessagesDTO(errors);
    }

    public ApplicationException(List<String> errors) {
        this.errorMessagesDTO = new ErrorMessagesDTO(errors);
    }
}
