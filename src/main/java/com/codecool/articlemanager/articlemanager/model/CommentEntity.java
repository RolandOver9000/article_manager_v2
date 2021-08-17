package com.codecool.articlemanager.articlemanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class CommentEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;

    private String body;

    @ManyToOne(cascade = {CascadeType.REMOVE})
    private ArticleEntity article;

    @ManyToOne(cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private UserEntity author;
}
