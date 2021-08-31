package com.codecool.articlemanager.articlemanager.model.entity;

import com.codecool.articlemanager.articlemanager.model.dto.IncomingArticleDTO;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
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

    @ManyToMany()
    private List<TagEntity> tagList;

    @ManyToOne(cascade = {CascadeType.REMOVE})
    private UserEntity author;

    @ToString.Exclude
    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private List<CommentEntity> comments;

    private int favoriteCount;

    @PrePersist
    private void updateTimestamp() {
        this.updateDateTime = new Date();
    }

    public static ArticleEntity transformIncomingDTO(IncomingArticleDTO articleDTO,
                                                     List<TagEntity> tags,
                                                     UserEntity author) {
        return ArticleEntity.builder()
                .author(author)
                .title(articleDTO.getTitle())
                .body(articleDTO.getBody())
                .description(articleDTO.getDescription())
                .tagList(tags)
                .build();
    }
}
