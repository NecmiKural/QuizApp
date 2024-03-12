package com.example.quizapp.services;

import com.example.quizapp.entities.Like;
import com.example.quizapp.entities.Post;
import com.example.quizapp.entities.User;
import com.example.quizapp.repos.PostRepository;
import com.example.quizapp.requests.PostCreateRequest;
import com.example.quizapp.requests.PostUpdateRequest;
import com.example.quizapp.responses.LikeResponse;
import com.example.quizapp.responses.PostResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private LikeService likeService;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    // calling with setter bcs in constructor there is a circular dependency,
    // calling eachother
    @Autowired
    public void setLikeService(LikeService likeService) {
        this.likeService = likeService;
    }

    public List<PostResponse> getPosts(Optional<Long> userId) {
        // gelen responsu post olarak almalıyız. aşağıda onun atamasını yapıyoruz.
        // Ardınran return ederken responsumuzun constructıryla dönüyoruz
        List<Post> list;
        if (userId.isPresent())
            list = postRepository.findByUserId(userId.get());
        list = postRepository.findAll();

        // direkt sql sorgusu atılabilir database'e

        return list.stream().map(p -> {
            List<LikeResponse> likes = likeService.getAllLikesWithParam(Optional.ofNullable(null),
                    Optional.of(p.getId()));
            return new PostResponse(p, likes);
        }).collect(Collectors.toList());

    }

    public Post getOnePostById(Long postId) {
        // orelsenull da olabilir
        return postRepository.findById(postId).orElseThrow();
    }

    public Post createPost(PostCreateRequest newPostRequest) {
        // kullanıcı yoksa post oluşturamaz
        User user = userService.getOneUserById(newPostRequest.getUserId());
        if (user == null)
            return null;
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
