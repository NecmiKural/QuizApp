package com.example.quizapp.requests;

import lombok.Data;

@Data
public class LikeCreateRequest {

    Long id;
    Long userId;
    Long postId;

}
