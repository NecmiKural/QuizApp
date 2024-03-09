package com.example.quizapp.repos;

import com.example.quizapp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllByPostId(Long postId);

    List<Like> findAllByUserId(Long userId);

    Like findByPostIdAndUserId(Long postId, Long userId);
}
