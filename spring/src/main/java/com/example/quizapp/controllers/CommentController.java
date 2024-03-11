package com.example.quizapp.controllers;

import com.example.quizapp.entities.Comment;
import com.example.quizapp.requests.CommentCreateRequest;
import com.example.quizapp.requests.CommentUpdateRequest;
import com.example.quizapp.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
        return commentService.getAllCommentsWithParams(userId, postId);
    }

    @PostMapping
    public Comment addComment(@RequestBody CommentCreateRequest request) {
        return commentService.addComment(request);
    }

    @GetMapping("/{commentId}")
//    @ResponseStatus(code = org.springframework.http.HttpStatus.OK, reason = "OK" + " comment found")
//    @ResponseBody()
//    @ExceptionHandler(org.springframework.web.bind.annotation.ResponseStatusException.class)
//    @ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
    public Comment getOneComment(@PathVariable Long commentId) {
        return commentService.getOneCommentById(commentId);
    }

    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest request) {
        return commentService.updateOneCommentById(commentId, request);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteOneCommentById(commentId);
    }

}
