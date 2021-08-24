package com.codecool.articlemanager.articlemanager.controller;

import com.codecool.articlemanager.articlemanager.model.dto.LoginDTO;
import com.codecool.articlemanager.articlemanager.model.dto.RegistrationDTO;
import com.codecool.articlemanager.articlemanager.service.LoginService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    private final LoginService loginService;

    @PostMapping("/login")
    public Long login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        log.info("Login request received: " + loginDTO.toString());
        return loginService.tryLogin(loginDTO, response);
    }

    @PostMapping("/registration")
    public ResponseEntity<RegistrationResponseDTO> registration(@RequestBody RegistrationDTO registrationDTO) {
        return ResponseEntity.ok(registrationService.register(registrationDTO));
    }

}
