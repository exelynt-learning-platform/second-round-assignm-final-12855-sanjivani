package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository repo;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return repo.save(user);
    }

   
    
     @PostMapping("/login")
    public String login(@RequestBody User user) {

        Optional<User> optionalUser = repo.findByEmail(user.getEmail());

        if (optionalUser.isPresent()) {
            User u = optionalUser.get();

            if (u.getPassword().equals(user.getPassword())) {
                return "Login Success";
            }
        }

        return "Invalid Credentials";
    }
}
