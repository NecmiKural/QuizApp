package com.example.quizapp.controllers;

import com.example.quizapp.entities.User;
import com.example.quizapp.repos.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//usersla alakalı tüm pathler buraya girecek genel olarak
@RequestMapping("/users")
public class UserController {

    // repodan modelimizi alıp query, işlem yapabilmemiz lazım
    // setter ya da constructor injection yapmamız lazım ki spring otomatik bulsun istediğimiz üzerinde işlem yapalım. Burada contstructor kullandıum
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        // jpa sağ olsun direnkt metodumuzu kullanabiliyoruz
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    // yukarıdaki pathimize ekleyecek
    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId) {

        // TODO: custom exception
        return userRepository.findById(userId).orElse(null);
    }

    // değiştirmek için, üzerine yazar ama
    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser) {
        Optional<User> user = userRepository.findById(userId);
        if ((user.isPresent())) {
            User foundUser = user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser);
            return foundUser;
        } else
            return null;
    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId) {
        userRepository.deleteById(userId);
    }

}
