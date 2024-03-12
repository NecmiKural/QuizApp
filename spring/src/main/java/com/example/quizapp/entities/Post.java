package com.example.quizapp.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "post")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    // Long userId;

    // hibernate, db relations
    // many to one bcs a user can have many posts
    // we are creating request object, so it dont have to be lazy
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    // I dont need all of the user's infos. I need only ID of the user. So we should
    // create request package for that
    User user;

    String title;
    @Lob
    // hibernate will recognise string as text from mysql. otherwise it could be
    // varchar255
    @Column(columnDefinition = "text")
    String text;
}
