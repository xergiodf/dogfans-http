package com.shawandpartners.dogfans.http.dto;

import com.shawandpartners.dogfans.http.enums.ErrorType;
import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sedf
 * @version 1 - 14.10.2017
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class ErrorResponse {

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private ErrorType errorType;
}
