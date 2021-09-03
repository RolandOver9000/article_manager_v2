package com.codecool.articlemanager.articlemanager.service;

import com.codecool.articlemanager.articlemanager.model.dto.UserDTO;
import com.codecool.articlemanager.articlemanager.model.entity.UserEntity;
import com.codecool.articlemanager.articlemanager.repository.UserRepository;
import com.codecool.articlemanager.articlemanager.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserEntity getUserById(Long id) {
        return userRepository.
                findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }

    public UserDTO getUserDetailsByJwtToken(String jwt) {
        Long userId = jwtService.getUserIdFromToken(jwt);
        return UserDTO.transformUserEntity(getUserById(userId));
    }

    private UserEntity updateUserEntityByUserDTO(UserEntity userEntity, UserDTO userDTO) {
        userEntity.setBio(userDTO.getBio());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setImage(userDTO.getImage());
        userEntity.setUsername(userDTO.getUsername());
        return userEntity;
    }

    public void updateUserDetailsById(UserDTO userDTO) {
        UserEntity searchedUser = getUserById(userDTO.getId());
        UserEntity updatedUser = updateUserEntityByUserDTO(searchedUser, userDTO);
        userRepository.save(updatedUser);
    }

    public List<UserDTO> getAllUserDetails() {
        List<UserEntity> foundUsers = userRepository.findAll();
        return foundUsers.stream().map(UserDTO::transformUserEntity).collect(Collectors.toList());
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
