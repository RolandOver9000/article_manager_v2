package com.codecool.articlemanager.articlemanager.service;

import com.codecool.articlemanager.articlemanager.model.dto.RegistrationDTO;
import com.codecool.articlemanager.articlemanager.model.entity.UserEntity;
import com.codecool.articlemanager.articlemanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegistrationService {

    private final PasswordEncoder encoder;
    private final RegistrationValidationService registrationValidationService;
    private final UserRepository userRepository;

    public void register(RegistrationDTO registrationData) {
        if(registrationValidationService.isRegistrationValid(registrationData)) {
            try {
                saveUser(registrationData);
            } catch (Exception e) {
                System.out.println("Error during persisting new user.");
            }
        }
    }

    private void saveUser(RegistrationDTO registrationData) {
        registrationData.setPassword(encoder.encode(registrationData.getPassword()));
        UserEntity newUser = UserEntity.transformDTO(registrationData);
        userRepository.save(newUser);
    }
}
