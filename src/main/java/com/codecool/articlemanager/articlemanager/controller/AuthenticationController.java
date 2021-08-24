package com.codecool.articlemanager.articlemanager.controller;

import com.codecool.articlemanager.articlemanager.model.dto.LoginDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @PostMapping("/login")
    public Long login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        log.info("Login request received: " + loginDTO.toString());
        return loginService.tryLogin(loginDTO, response);
    }

}
