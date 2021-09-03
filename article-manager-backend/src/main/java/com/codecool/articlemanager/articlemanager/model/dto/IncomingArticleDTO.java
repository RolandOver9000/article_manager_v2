package com.codecool.articlemanager.articlemanager.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class IncomingArticleDTO {

    private Long id;
    private String title;
    private String description;
    private String body;
    private List<String> tagList;

}
