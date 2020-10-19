package com.globallogic.userMaintainer.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
@ToString
@Component
public class UserRequestDTO {

    @NotNull(message = "must not be empty")
    private String name;
    @NotNull(message = "must not be empty")
    @Pattern(regexp = "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-z]{2,4}", message = "must follow the pattern (aaaaaaa@dominio.cl)")
    private String email;
    @NotNull(message = "must not be empty")
    @Size(min = 4, message = "must contain at least 4 characters")
    //@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=(.*\\\\d){2})[a-zA-Z\\\\d]{4,}$", message = "Password debe seguir el patron ")
    private String password;

    @NotEmpty(message = "must not be empty")
    @Valid
    private List<PhoneDTO> phones;
}
