package com.example.quizapp.controllers;

import com.example.quizapp.entities.Post;
import com.example.quizapp.requests.PostCreateRequest;
import com.example.quizapp.requests.PostUpdateRequest;
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
    // direkt post objesinden değil, sadece userın IDsine ihtiyaç duyduğumuz için request için gerekli olan bilgilerdeki classımızdan çağırıyopruz
    public Post createPost(@RequestBody PostCreateRequest newPostRequest) {
        return postService.createPost(newPostRequest);
    }

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable Long postId) {
        return postService.getOnePostById(postId);
    }

    @PutMapping("/{postId}")
    // we want to update only specific places, not everything about the post. so we are calling a request object for that
    public Post updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest updatePost) {
        return postService.updatePost(postId, updatePost);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }
}
