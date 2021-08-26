package com.codecool.articlemanager.articlemanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDTO {

    private String email;
    private String password;

}
