package com.codecool.articlemanager.articlemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class CommentEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;

    private String body;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = {CascadeType.REMOVE})
    private ArticleEntity article;

    @ManyToOne(cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private UserEntity author;
}
