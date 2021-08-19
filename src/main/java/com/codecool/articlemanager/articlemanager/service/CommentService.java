package com.codecool.articlemanager.articlemanager.service;

import com.codecool.articlemanager.articlemanager.model.CommentEntity;
import com.codecool.articlemanager.articlemanager.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public List<CommentEntity> getCommentsByArticleId(Long id) {
        return commentRepository.findAllByArticleId(id);
    }
}
