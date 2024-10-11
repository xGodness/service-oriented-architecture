package ru.xgodness.exception;

import ru.xgodness.exception.dto.ErrorMessages;

import java.util.List;

public class AlreadyExistsException extends ApplicationException {
    public AlreadyExistsException(ErrorMessages errorMessages) {
        super(errorMessages);
    }

    public AlreadyExistsException(String... errors) {
        super(errors);
    }

    public AlreadyExistsException(List<String> errors) {
        super(errors);
    }
}
