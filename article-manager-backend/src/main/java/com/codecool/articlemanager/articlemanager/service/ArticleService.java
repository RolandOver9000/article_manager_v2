package com.codecool.articlemanager.articlemanager.service;

import com.codecool.articlemanager.articlemanager.model.entity.ArticleEntity;
import com.codecool.articlemanager.articlemanager.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
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

    public Long updateArticleById(ArticleEntity updatedArticle) {
        ArticleEntity articleEntity = articleRepository.save(updatedArticle);
        return articleEntity.getId();
    }

    public void deleteArticle(Long id) {
        try {
            articleRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Error during article deletion.");
        }
    }
}
