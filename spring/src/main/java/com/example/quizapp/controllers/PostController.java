package com.example.quizapp.controllers;

import com.example.quizapp.entities.Post;
import com.example.quizapp.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // with request param way we can use this function with two ways: /posts and /posts?userId={userId}. other parameters can be added also
    @GetMapping
    public List<Post> getPosts(@RequestParam Optional<Long> userId) {
        return postService.getPosts(userId);
    }

    @PostMapping
    public Post createPost(@RequestBody Post newPost) {
        return postService.createPost(newPost);
    }

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }
}
