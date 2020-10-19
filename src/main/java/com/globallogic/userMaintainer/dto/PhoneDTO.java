package com.globallogic.userMaintainer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;

@Setter
@Getter
@ToString
@Component
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {

    @Size(min = 5, message = "entered is not valid, min 5 characters")
    private String number;
    @Size(min = 1, max = 3, message = "not valid, min 3, max 4 characters")
    private String cityCode;
    @Size(min = 1, max = 2, message = "not valid, min 1, max 2 characters")
    private String countryCode;
    @JsonIgnore
    private int idUser;
}
