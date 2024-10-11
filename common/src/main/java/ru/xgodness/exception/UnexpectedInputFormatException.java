package ru.xgodness.exception;

import ru.xgodness.exception.dto.ErrorMessages;

import java.util.List;

public class UnexpectedInputFormatException extends ApplicationException {
    public UnexpectedInputFormatException(ErrorMessages errorMessages) {
        super(errorMessages);
    }

    public UnexpectedInputFormatException(String... errors) {
        super(errors);
    }

    public UnexpectedInputFormatException(List<String> errors) {
        super(errors);
    }
}
