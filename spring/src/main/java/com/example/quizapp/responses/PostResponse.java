package com.example.quizapp.responses;

import com.example.quizapp.entities.Post;

import lombok.Data;

@Data
// we should prevent some informations shouldnt return. we should control
// response here
public class PostResponse {
    Long id;
    Long userId;
    String userName;
    String title;
    String text;

    // constructer, mapper
    public PostResponse(Post entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUserName();
        this.title = entity.getTitle();
        this.text = entity.getText();
    }
}
