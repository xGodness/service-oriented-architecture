package ru.xgodness.endpoint.faculties.model.response;

import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * This class is necessary since precious Mule ESB is unable to handle soap faults unless it expects non empty response from method
 *
 * That's just hilarious tbh
 */
@XmlRootElement
public class CheckFacultyAndDisciplineExistenceResponse {
}
