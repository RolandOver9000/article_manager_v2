package com.codecool.articlemanager.articlemanager.controller;

import com.codecool.articlemanager.articlemanager.model.ArticleEntity;
import com.codecool.articlemanager.articlemanager.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/articles")
@CrossOrigin(origins = "*")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("")
    public ResponseEntity<List<ArticleEntity>> getAllArticles() {
        return ResponseEntity.ok(articleService.getAllArticles());
    }
}
