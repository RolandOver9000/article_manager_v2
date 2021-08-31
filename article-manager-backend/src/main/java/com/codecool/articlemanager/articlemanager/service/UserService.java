package com.codecool.articlemanager.articlemanager.service;

import com.codecool.articlemanager.articlemanager.model.entity.UserEntity;
import com.codecool.articlemanager.articlemanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity getUserById(Long id) {
        return userRepository.
                findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }
}
