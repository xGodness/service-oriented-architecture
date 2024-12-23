package ru.xgodness.endpoint.labworks.model.request;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class GetLabworkByIdRequest {
    private long id;

    @XmlElement(required = true)
    public long getId() {
        return id;
    }
}
