package com.example.quizapp.requests;

import lombok.Data;

@Data
public class PostCreateRequest {

    // for now
    Long id;
    String text;
    String title;
    Long userId;
}
