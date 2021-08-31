package com.codecool.articlemanager.articlemanager.service;

import com.codecool.articlemanager.articlemanager.model.dto.IncomingArticleDTO;
import com.codecool.articlemanager.articlemanager.model.entity.ArticleEntity;
import com.codecool.articlemanager.articlemanager.model.entity.TagEntity;
import com.codecool.articlemanager.articlemanager.model.entity.UserEntity;
import com.codecool.articlemanager.articlemanager.repository.ArticleRepository;
import com.codecool.articlemanager.articlemanager.security.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final TagService tagService;
    private final JwtService jwtService;
    private final UserService userService;

    public List<ArticleEntity> getAllArticles() {
        return articleRepository.findAll();
    }

    public Long saveArticle(IncomingArticleDTO newArticle, String jwtToken) {
        Long id = jwtService.getUserIdFromToken(jwtToken);
        UserEntity author = userService.getUserById(id);
        List<TagEntity> tags = tagService.saveMultipleStringTags(newArticle.getTagList());
        ArticleEntity transformedArticleDTO = ArticleEntity.transformIncomingDTO(newArticle, tags, author);
        ArticleEntity savedArticle = articleRepository.save(transformedArticleDTO);
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
