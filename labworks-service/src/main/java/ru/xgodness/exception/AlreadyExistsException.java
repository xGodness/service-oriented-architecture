package exception;

import exception.model.dto.ErrorMessagesDTO;

import java.util.List;

public class AlreadyExistsException extends ApplicationException {
    public AlreadyExistsException(ErrorMessagesDTO errorMessagesDTO) {
        super(errorMessagesDTO);
    }

    public AlreadyExistsException(String... errors) {
        super(errors);
    }

    public AlreadyExistsException(List<String> errors) {
        super(errors);
    }
}
