package ru.xgodness.exception;

import lombok.Getter;
import ru.xgodness.exception.dto.ErrorMessages;

import java.util.List;

@Getter
public class ValidationException extends ApplicationException {
    public ValidationException(ErrorMessages errorMessages) {
        super(errorMessages);
    }

    public ValidationException(String... errors) {
        super(errors);
    }

    public ValidationException(List<String> errors) {
        super(errors);
    }
}
