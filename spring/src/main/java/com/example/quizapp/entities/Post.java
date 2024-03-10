package com.example.quizapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "post")
@Data
public class Post {

    @Id
    Long id;
    //Long userId;

    //hibernate, db relations
    //many to one bcs a user can have many posts
    // fetch type lazy mean it may wait for the data
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    User user;

    String title;
    @Lob
    // hibernate will recognise string as text from mysql. otherwise it could be varchar255
    @Column(columnDefinition = "TEXT")
    String text;
}
