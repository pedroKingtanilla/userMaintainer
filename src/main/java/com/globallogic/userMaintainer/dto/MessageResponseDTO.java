package com.globallogic.userMaintainer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class MessageResponseDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private HttpStatus statusCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ErrorDTO errorDTO;
}
