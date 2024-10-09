package exception.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessagesDTO {
    private List<String> messages;

    public ErrorMessagesDTO(String... errors) {
        messages = List.of(errors);
    }
}
