package com.codecool.articlemanager.articlemanager.model.dto;

import com.codecool.articlemanager.articlemanager.model.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String bio;
    private String email;
    private Long id;
    private String image;
    private String username;

    public static UserDTO transformUserEntity(UserEntity userEntity) {
        return UserDTO.builder()
                .bio(userEntity.getBio())
                .email(userEntity.getEmail())
                .id(userEntity.getId())
                .image(userEntity.getImage())
                .username(userEntity.getUsername())
                .build();
    }
}
