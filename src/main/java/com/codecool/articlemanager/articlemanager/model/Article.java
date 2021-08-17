package com.codecool.articlemanager.articlemanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Article {

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

    @OneToMany(fetch = FetchType.EAGER)
    private List<Comment> comments;

    private int favoriteCount;

    @PrePersist
    private void updateTimestamp() {
        this.updateDateTime = new Date();
    }
}
