package ru.xgodness.exception;

import ru.xgodness.exception.dto.ErrorMessages;

import java.util.List;

public class NotFoundException extends ApplicationException {
    public NotFoundException(ErrorMessages errorMessages) {
        super(errorMessages);
    }

    public NotFoundException(String... errors) {
        super(errors);
    }

    public NotFoundException(List<String> errors) {
        super(errors);
    }
}
