package com.example.quizapp.services;

import com.example.quizapp.entities.Post;
import com.example.quizapp.repos.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPosts(Optional<Long> userId) {
        if (userId.isPresent())
            return postRepository.findByUserId(userId.get());
        return postRepository.findAll();

    }

    public Post getPost(Long postId) {
        // orelsenull da olabilir
        return postRepository.findById(postId).orElseThrow();
    }

    public Post createPost(Post newPost) {
        return postRepository.save(newPost);
    }
}
