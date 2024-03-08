package com.example.quizapp.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

//Database'e mapleneceğini gösteriyoruz
@Entity
@Table(name = "user")
//getter ve setterları otomatik generate ediyor
@Data
public class User {
    @Id
    Long id;

    String userName;
    String password;
}
