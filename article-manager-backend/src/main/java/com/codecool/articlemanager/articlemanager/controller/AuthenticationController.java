package com.codecool.articlemanager.articlemanager.controller;

import com.codecool.articlemanager.articlemanager.model.dto.LoginDTO;
import com.codecool.articlemanager.articlemanager.model.dto.RegistrationDTO;
import com.codecool.articlemanager.articlemanager.security.service.JwtService;
import com.codecool.articlemanager.articlemanager.service.LoginService;
import com.codecool.articlemanager.articlemanager.service.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    private final LoginService loginService;
    private final RegistrationService registrationService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        log.info("Login request received: " + loginDTO.toString());
        try {
            return ResponseEntity.ok(loginService.tryLogin(loginDTO, response));
        } catch (Exception e) {
            log.error("Error during login.");
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Error during login.");
        }
    }

    @GetMapping("/is-logged-in")
    public ResponseEntity<Boolean> isLoggedIn(@CookieValue(value="JWT" ,required=false) String jwt) {
        log.info("Check if user logged in with token: " + jwt);
        if(jwt != null && jwtService.validateToken(jwt)) {
            log.info("User is logged in.");
            return ResponseEntity.ok(true);
        }
        log.error("User is not logged in.");
        throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED, "User is not logged in.");
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody RegistrationDTO registrationDTO) {
        log.info("Registration request received: " + registrationDTO.toString());
        try {
            registrationService.register(registrationDTO);
            return ResponseEntity.ok("Registration is successful.");
        } catch (Exception e) {
            log.error("Error during registration.");
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Wrong e-mail or username.");
        }
    }

}
