package com.codecool.articlemanager.articlemanager.service;

import com.codecool.articlemanager.articlemanager.model.dto.IncomingComment;
import com.codecool.articlemanager.articlemanager.model.entity.ArticleEntity;
import com.codecool.articlemanager.articlemanager.model.entity.CommentEntity;
import com.codecool.articlemanager.articlemanager.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleService articleService;

    public List<CommentEntity> getCommentsByArticleId(Long id) {
        return commentRepository.findAllByArticleId(id);
    }

    public void saveCommentByArticleId(Long articleId, IncomingComment comment) {
        ArticleEntity articleEntity = articleService.getArticleById(articleId);
        CommentEntity commentEntity = CommentEntity.builder()
                .article(articleEntity)
                .body(comment.getBody())
                .build();
        commentRepository.save(commentEntity);
    }
}
