package com.codecool.articlemanager.articlemanager.controller;

import com.codecool.articlemanager.articlemanager.model.dto.IncomingComment;
import com.codecool.articlemanager.articlemanager.model.entity.ArticleEntity;
import com.codecool.articlemanager.articlemanager.model.entity.CommentEntity;
import com.codecool.articlemanager.articlemanager.service.ArticleService;
import com.codecool.articlemanager.articlemanager.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/articles")
@CrossOrigin(origins = "*")
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;

    @GetMapping("")
    public ResponseEntity<List<ArticleEntity>> getAllArticles() {
        return ResponseEntity.ok(articleService.getAllArticles());
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

    //Endpoints for article comments

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentEntity>> getAllCommentsByArticleId(@PathVariable(value="id") Long id) {
        return ResponseEntity.ok(commentService.getCommentsByArticleId(id));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<String> saveCommentForArticle(@PathVariable(value="id") Long articleId,
                                                        @RequestBody IncomingComment comment) {
        try{
            commentService.saveCommentByArticleId(articleId, comment);
            return ResponseEntity.ok("Comment successfully saved.");
        } catch (Exception e) {
            System.out.println("Error during saving a comment.");
        }
            return ResponseEntity.ok("There were a problem during saving your comment.");
    }
}
