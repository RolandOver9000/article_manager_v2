package com.codecool.articlemanager.articlemanager.model.entity;

import com.codecool.articlemanager.articlemanager.model.entity.ArticleEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue
    private Long id;

    @Column(unique=true)
    private String tag;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(mappedBy = "tagList")
    private List<ArticleEntity> articleEntity;
}
