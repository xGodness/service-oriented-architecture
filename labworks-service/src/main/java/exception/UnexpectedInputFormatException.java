package exception;

import model.dto.ErrorMessagesDTO;

import java.util.List;

public class UnexpectedInputFormatException extends ApplicationException {
    public UnexpectedInputFormatException(ErrorMessagesDTO errorMessagesDTO) {
        super(errorMessagesDTO);
    }

    public UnexpectedInputFormatException(String... errors) {
        super(errors);
    }

    public UnexpectedInputFormatException(List<String> errors) {
        super(errors);
    }
}
