package com.example.quizapp.services;

import com.example.quizapp.entities.Post;
import com.example.quizapp.entities.User;
import com.example.quizapp.repos.PostRepository;
import com.example.quizapp.requests.PostCreateRequest;
import com.example.quizapp.requests.PostUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<Post> getPosts(Optional<Long> userId) {
        if (userId.isPresent()) return postRepository.findByUserId(userId.get());
        return postRepository.findAll();

    }

    public Post getPost(Long postId) {
        // orelsenull da olabilir
        return postRepository.findById(postId).orElseThrow();
    }

    public Post createPost(PostCreateRequest newPostRequest) {
        // kullanıcı yoksa post oluşturamaz
        User user = userService.getOneUserById(newPostRequest.getUserId());
        if (user == null) return null;
        Post toSave = new Post();
        toSave.setId(newPostRequest.getId());
        toSave.setText(newPostRequest.getText());
        toSave.setTitle(newPostRequest.getTitle());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }

    public Post updatePost(Long postId, PostUpdateRequest updatePost) {
        // lets check if post exist first
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            Post toUpdate = post.get();
            toUpdate.setText(updatePost.getText());
            toUpdate.setTitle(updatePost.getTitle());
            return postRepository.save(toUpdate);
        }
        return null;
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
