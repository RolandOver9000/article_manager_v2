package com.codecool.articlemanager.articlemanager.controller;

import com.codecool.articlemanager.articlemanager.model.ArticleEntity;
import com.codecool.articlemanager.articlemanager.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("")
    public ResponseEntity<Long> saveArticle(@RequestBody ArticleEntity newArticle) {
        return ResponseEntity.ok(articleService.saveArticle(newArticle));
    }
}
