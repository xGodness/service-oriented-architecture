package exception;

import exception.model.dto.ErrorMessagesDTO;

import java.util.List;

public class NotFoundException extends ApplicationException {
    public NotFoundException(ErrorMessagesDTO errorMessagesDTO) {
        super(errorMessagesDTO);
    }

    public NotFoundException(String... errors) {
        super(errors);
    }

    public NotFoundException(List<String> errors) {
        super(errors);
    }
}
