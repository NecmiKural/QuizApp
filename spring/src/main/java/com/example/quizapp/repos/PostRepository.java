package com.example.quizapp.repos;

import com.example.quizapp.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    // with this way we can combine and customize jpa functions
    List<Post> findByUserId(Long userId);
}
