package com.codecool.articlemanager.articlemanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class RegistrationDTO {

    private String email;
    private String username;
    private String password;

}
