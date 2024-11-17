package ru.xgodness.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessages implements Serializable {
    private List<String> messages;

    public ErrorMessages(String... errors) {
        messages = List.of(errors);
    }
}
