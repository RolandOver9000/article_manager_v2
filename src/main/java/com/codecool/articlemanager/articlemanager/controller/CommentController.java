package com.codecool.articlemanager.articlemanager.controller;

import com.codecool.articlemanager.articlemanager.model.dto.IncomingComment;
import com.codecool.articlemanager.articlemanager.model.entity.CommentEntity;
import com.codecool.articlemanager.articlemanager.service.CommentService;
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
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentEntity>> getAllCommentsByArticleId(@PathVariable(value="id") Long id) {
        log.info("Received get request for article comments. ArticleId: " + id.toString());
        try {
            return ResponseEntity.ok(commentService.getCommentsByArticleId(id));
        } catch (Exception e) {
            log.error("Error during getting comments for article. ArticleId: " + id.toString());
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error during getting comments for article. ArticleId: " + id.toString());
        }
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<String> saveCommentForArticle(@PathVariable(value="id") Long id,
                                                        @RequestBody IncomingComment comment) {
        log.info("Received post request for saving comment. ArticleId: " + id.toString());
        try {
            commentService.saveCommentByArticleId(id, comment);
            return ResponseEntity.ok("Comment successfully saved.");
        } catch (Exception e) {
            log.error("Error during saving comment for article. ArticleId: " + id.toString());
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error during saving comment for article. ArticleId: " + id.toString());
        }
    }

    @DeleteMapping("/{articleId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentById(@PathVariable(value="articleId") Long articleId,
                                                    @PathVariable(value="commentId") Long commentId) {
        try{
            commentService.deleteCommentById(commentId);
            return ResponseEntity.ok("Comment successfully delete.");
        } catch (Exception e) {
            System.out.println("Error during the comment deletion process.");
        }
        return ResponseEntity.ok("There was a problem during deleting your comment.");
    }
}
