package com.codecool.articlemanager.articlemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class TagEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String tag;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(mappedBy = "tagList")
    private List<ArticleEntity> articleEntity;
}
