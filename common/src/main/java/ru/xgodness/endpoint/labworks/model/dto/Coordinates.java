package ru.xgodness.endpoint.labworks.model.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.io.Serializable;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement
public class Coordinates implements Serializable {
    private Long x;
    private Integer y;

    @XmlElement(required = true)
    public Long getX() {
        return x;
    }

    @XmlElement(required = true)
    public Integer getY() {
        return y;
    }
}
