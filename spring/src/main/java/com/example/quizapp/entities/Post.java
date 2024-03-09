package com.example.quizapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "post")
@Data
public class Post {

    @Id
    Long id;
    Long userId;
    String title;
    @Lob
    // hibernate will recognise string as text from mysql. otherwise it could be varchar255
    @Column(columnDefinition = "TEXT")
    String text;
}
