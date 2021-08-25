package com.codecool.articlemanager.articlemanager.controller;

import com.codecool.articlemanager.articlemanager.model.dto.LoginDTO;
import com.codecool.articlemanager.articlemanager.model.dto.RegistrationDTO;
import com.codecool.articlemanager.articlemanager.service.LoginService;
import com.codecool.articlemanager.articlemanager.service.RegistrationService;
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
    private final RegistrationService registrationService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        log.info("Login request received: " + loginDTO.toString());
        try {
            return ResponseEntity.ok(loginService.tryLogin(loginDTO, response));
        } catch (Exception e) {
            log.info("Error during login.");
            return ResponseEntity.ok("Error during login.");
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody RegistrationDTO registrationDTO) {
        try {
            registrationService.register(registrationDTO);
            return ResponseEntity.ok("Registration is successful.");
        } catch (Exception e) {
            System.out.println("Error during registration.");
        }
        return ResponseEntity.ok("Error during registration.");
    }

}
