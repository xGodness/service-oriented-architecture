package endpoint.labworks.util;

import exception.UnexpectedInputFormatException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public abstract class QueryToken {
    private LabworkField field;

    void setField(String field) {
        try {
            this.field = LabworkField.fromString(field);
        } catch (IllegalArgumentException ex) {
            throw new UnexpectedInputFormatException("Cannot parse field name, must be one of: "
                    + Arrays.stream(LabworkField.values()).map(LabworkField::getLiteral).toList());
        }
    }
}
