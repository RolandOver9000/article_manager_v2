package com.codecool.articlemanager.articlemanager.controller;

import com.codecool.articlemanager.articlemanager.model.dto.IncomingArticleDTO;
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
        log.info("Received get request for getting article by id: " + id.toString());
        try {
            return ResponseEntity.ok(articleService.getArticleById(id));
        } catch (Exception e) {
            log.error("Error during getting article by id: " + id.toString());
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error during getting article by id: " + id.toString());
        }
    }

    @PostMapping("")
    public ResponseEntity<Long> saveArticle(@RequestBody IncomingArticleDTO newArticle) {
        log.info("Received post request for saving article.");
        try {
            return ResponseEntity.ok(articleService.saveArticle(newArticle));
        } catch (Exception e) {
            log.error("Error during saving article.");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error during saving article.");
        }
    }

    @PutMapping("")
    public ResponseEntity<Long> updateArticle(@RequestBody ArticleEntity updatedArticle) {
        log.info("Received update request for updating article.");
        try {
            return ResponseEntity.ok(articleService.updateArticleById(updatedArticle));
        } catch (Exception e) {
            log.error("Error during updating article.");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error during updating article.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticleById(@PathVariable(value="id") Long id) {
        log.info("Received delete request for deleting article.");
        try {
            articleService.deleteArticle(id);
            return ResponseEntity.ok("Article successfully deleted.");
        } catch (Exception e) {
            log.error("Error during deleting article.");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error during deleting article.");
        }
    }
}
