package com.codecool.articlemanager.articlemanager.service;

import com.codecool.articlemanager.articlemanager.model.ArticleEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<ArticleEntity> getAllArticles() {
        return articleRepository.getAllArticles();
    }
}
