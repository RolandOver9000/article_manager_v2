package com.codecool.articlemanager.articlemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ArticleEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    private String body;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDateTime;

    @ElementCollection
    private List<String> tagList;

    @ManyToOne(cascade = {CascadeType.REMOVE})
    private UserEntity author;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private List<Comment> comments;

    private int favoriteCount;

    @PrePersist
    private void updateTimestamp() {
        this.updateDateTime = new Date();
    }
}