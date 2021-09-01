package com.codecool.articlemanager.articlemanager.model.entity;

import com.codecool.articlemanager.articlemanager.model.entity.ArticleEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class TagEntity {

    @Id
    @JsonIgnore
    @GeneratedValue
    private Long id;

    @Column(unique=true)
    @Nullable
    private String tag;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany
    private List<ArticleEntity> articleEntities;
}
