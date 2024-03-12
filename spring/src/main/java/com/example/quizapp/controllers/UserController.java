package com.example.quizapp.controllers;

import com.example.quizapp.entities.User;
import com.example.quizapp.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//usersla alakalı tüm pathler buraya girecek genel olarak
@RequestMapping("/users")
public class UserController {

    //service layerda yapıyoruz işlemleri. Business logic. business logic
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        return userService.saveOneUser(newUser);
    }

    // yukarıdaki pathimize ekleyecek
    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId) {
        return userService.getOneUserById(userId);
    }

    // değiştirmek için, üzerine yazar ama
    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser) {
        return userService.updateOneUser(userId, newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId) {
        userService.deleteById(userId);
    }

}
