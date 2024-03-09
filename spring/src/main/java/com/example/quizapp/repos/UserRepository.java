package com.example.quizapp.repos;

import com.example.quizapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

// jpa, database'e metotlu query atmamızı sağlıyor. findall metodu gibi hazır
public interface UserRepository extends JpaRepository<User, Long> {

}
