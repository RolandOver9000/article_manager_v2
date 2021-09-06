package com.codecool.articlemanager.articlemanager.controller;

import com.codecool.articlemanager.articlemanager.model.dto.UserDTO;
import com.codecool.articlemanager.articlemanager.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @GetMapping("/logged-in")
    public ResponseEntity<UserDTO> getUserDetails(@CookieValue(value="JWT") String jwt) {
        log.info("Checking if user is logged in with token:" + jwt);
        try{
            return ResponseEntity.ok(userService.getUserDetailsByJwtToken(jwt));
        } catch (Exception e) {
            log.error("User is not logged in or the server has some problems.");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error during getting user details.");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getUserDetails() {
        log.info("Received a get request for getting all users' details. ");
        try{
            return ResponseEntity.ok(userService.getAllUserDetails());
        } catch (Exception e) {
            log.error("Error during getting all users' details.");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error during getting all user details.");
        }
    }

    @PutMapping("")
    public ResponseEntity<String> updateUserDetails(@RequestBody UserDTO userDTO) {
        try{
            userService.updateUserDetailsById(userDTO);
            return ResponseEntity.ok("User successfully updated.");
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error during updating user details.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable(value = "id") Long id) {
        try{
            userService.deleteUserById(id);
            return ResponseEntity.ok("User successfully deleted.");
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error during deleting user.");
        }
    }
}
