package com.codecool.articlemanager.articlemanager.service;

import com.codecool.articlemanager.articlemanager.model.ArticleEntity;
import com.codecool.articlemanager.articlemanager.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<ArticleEntity> getAllArticles() {
        return articleRepository.findAll();
    }

    public Long saveArticle(ArticleEntity newArticle) {
        ArticleEntity savedArticle = articleRepository.save(newArticle);
        return savedArticle.getId();
    }

    public ArticleEntity getArticleById(Long id) {
        return articleRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article not found by id: " + id));
    }
}
