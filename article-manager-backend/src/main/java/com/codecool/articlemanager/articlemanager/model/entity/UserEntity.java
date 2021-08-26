package com.codecool.articlemanager.articlemanager.model.entity;

import com.codecool.articlemanager.articlemanager.model.dto.RegistrationDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String email;

    private String bio;

    private String image;

    private String password;

    @ManyToMany(cascade = {CascadeType.REMOVE})
    private List<ArticleEntity> favorites;

    @ElementCollection
    private List<String> roles;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "author")
    @EqualsAndHashCode.Exclude
    private List<ArticleEntity> articleEntities;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "author")
    @EqualsAndHashCode.Exclude
    private List<CommentEntity> comments;

    public static UserEntity transformDTO(RegistrationDTO registrationData) {
        return UserEntity.builder()
                .username(registrationData.getUsername())
                .email(registrationData.getEmail())
                .password(registrationData.getPassword())
                .build();
    }
}
