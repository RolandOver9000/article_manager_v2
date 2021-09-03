package com.codecool.articlemanager.articlemanager.model.dto;

import com.codecool.articlemanager.articlemanager.model.entity.ArticleEntity;
import com.codecool.articlemanager.articlemanager.model.entity.TagEntity;
import io.jsonwebtoken.lang.Collections;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutgoingArticleDTO {

    private Long id;
    private String title;
    private String description;
    private String body;
    private List<String> tagList;

    public static OutgoingArticleDTO transformArticleEntity(ArticleEntity articleEntity) {
        List<String> tagList = articleEntity
                .getTagList()
                .stream()
                .map(TagEntity::getTag)
                .collect(Collectors.toList());

        return OutgoingArticleDTO.builder()
                .id(articleEntity.getId())
                .body(articleEntity.getBody())
                .description(articleEntity.getDescription())
                .title(articleEntity.getTitle())
                .tagList(tagList)
                .build();
    }
}
