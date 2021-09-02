package com.codecool.articlemanager.articlemanager.model.dto;

import com.codecool.articlemanager.articlemanager.model.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OutgoingUserDTO {
    private String bio;
    private String email;
    private Long id;
    private String imageUrl;
    private String username;

    public static OutgoingUserDTO transformUserEntity(UserEntity userEntity) {
        return OutgoingUserDTO.builder()
                .bio(userEntity.getBio())
                .email(userEntity.getEmail())
                .id(userEntity.getId())
                .imageUrl(userEntity.getImage())
                .username(userEntity.getUsername())
                .build();
    }
}
