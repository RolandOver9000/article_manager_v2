package com.codecool.articlemanager.articlemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
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

}
