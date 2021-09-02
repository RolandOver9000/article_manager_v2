package com.codecool.articlemanager.articlemanager.controller;

import com.codecool.articlemanager.articlemanager.model.dto.OutgoingUserDTO;
import com.codecool.articlemanager.articlemanager.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<OutgoingUserDTO> getUserDetails(@CookieValue(value="JWT") String jwt) {
        try{
            return ResponseEntity.ok(userService.getUserDetailsByJwtToken(jwt));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error during getting user details.");
        }
    }
}
