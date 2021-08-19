package com.codecool.articlemanager.articlemanager.service;

import com.codecool.articlemanager.articlemanager.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
}
