package exception;

import lombok.Getter;
import model.dto.ErrorMessagesDTO;

import java.util.List;

@Getter
public class ValidationException extends ApplicationException {
    public ValidationException(ErrorMessagesDTO errorMessagesDTO) {
        super(errorMessagesDTO);
    }

    public ValidationException(String... errors) {
        super(errors);
    }

    public ValidationException(List<String> errors) {
        super(errors);
    }
}
