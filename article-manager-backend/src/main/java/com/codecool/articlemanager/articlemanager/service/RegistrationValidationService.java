package com.codecool.articlemanager.articlemanager.service;

import com.codecool.articlemanager.articlemanager.model.dto.RegistrationDTO;
import com.codecool.articlemanager.articlemanager.model.entity.UserEntity;
import com.codecool.articlemanager.articlemanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationValidationService {

    private final UserRepository userRepository;

    public boolean isRegistrationValid(RegistrationDTO registrationData) {
        //try to make 1 query that check these
        return isUsernameValid(registrationData.getUsername()) &&
                isEmailValid(registrationData.getEmail());
    }

    private boolean isUsernameValid(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }

    private boolean isEmailValid(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }
}
