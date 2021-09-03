package com.codecool.articlemanager.articlemanager.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.Nullable;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
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
