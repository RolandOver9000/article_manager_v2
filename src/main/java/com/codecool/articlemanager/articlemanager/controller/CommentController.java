package com.codecool.articlemanager.articlemanager.controller;

import com.codecool.articlemanager.articlemanager.model.dto.IncomingComment;
import com.codecool.articlemanager.articlemanager.model.entity.CommentEntity;
import com.codecool.articlemanager.articlemanager.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/articles")
@CrossOrigin(origins = "*")
public class CommentController {

    private final CommentService commentService;

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
