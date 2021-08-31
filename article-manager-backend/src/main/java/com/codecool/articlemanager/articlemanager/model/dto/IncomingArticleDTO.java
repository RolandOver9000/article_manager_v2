package com.codecool.articlemanager.articlemanager.model.dto;

import com.codecool.articlemanager.articlemanager.model.entity.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class IncomingArticleDTO {

    private Long id;
    private UserEntity author;
    private String title;
    private String description;
    private String body;
    private List<String> tagList;

}
