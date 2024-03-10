package com.example.quizapp.services;

import com.example.quizapp.entities.User;
import com.example.quizapp.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    // repodan modelimizi alıp query, işlem yapabilmemiz lazım
    UserRepository userRepository;

    // setter ya da constructor injection yapmamız lazım ki spring otomatik bulsun istediğimiz üzerinde işlem yapalım. Burada contstructor kullandıum
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        // jpa sağ olsun direnkt metodumuzu kullanabiliyoruz
        return userRepository.findAll();
    }

    public User saveOneUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User getOneUserById(Long userId) {
        // TODO: custom exception
        return userRepository.findById(userId).orElse(null);
    }

    public User updateOneUser(Long userId, User newUser) {
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

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }
}
