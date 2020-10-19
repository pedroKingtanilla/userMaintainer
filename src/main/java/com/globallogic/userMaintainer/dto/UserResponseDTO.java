package com.globallogic.userMaintainer.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserResponseDTO {

    private int id;
    private String name;
    private String email;
    private String password;
    private String created;
    private String modified;
    private String lastLogin;
    private String token;
    private boolean isactive;
    private List<PhoneDTO> phones;


}
