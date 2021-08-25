package com.codecool.articlemanager.articlemanager.controller;

import com.codecool.articlemanager.articlemanager.model.entity.ArticleEntity;
import com.codecool.articlemanager.articlemanager.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/articles")
@CrossOrigin(origins = "*")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("")
    public ResponseEntity<List<ArticleEntity>> getAllArticles() {
        log.info("Received get request for getting all articles.");
        try {
            return ResponseEntity.ok(articleService.getAllArticles());
        } catch (Exception e) {
            log.error("Error during getting all articles.");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error during getting all articles.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleEntity> getArticleById(@PathVariable(value="id") Long id) {
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @PostMapping("")
    public ResponseEntity<Long> saveArticle(@RequestBody ArticleEntity newArticle) {
        return ResponseEntity.ok(articleService.saveArticle(newArticle));
    }

    @PutMapping("")
    public ResponseEntity<Long> updateArticle(@RequestBody ArticleEntity updatedArticle) {
        return ResponseEntity.ok(articleService.updateArticleById(updatedArticle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticleById(@PathVariable(value="id") Long id) {
        boolean isArticleDeleted = articleService.deleteArticle(id);
        if(isArticleDeleted) {
            return ResponseEntity.ok("Successfully deleted the article.");
        }
        return ResponseEntity.ok("There were a problem during the deletion process.");
    }
}
