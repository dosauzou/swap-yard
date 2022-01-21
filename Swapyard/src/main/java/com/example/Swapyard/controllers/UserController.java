package com.example.Swapyard.controllers;

//import com.example.Swapyard.models.CustomUserDetails;

import com.example.Swapyard.models.Role;
import com.example.Swapyard.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.Swapyard.repositories.UserRepository;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token"})
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("users-list")
    public List<User> list() {
        return userRepository.findAll();
    }

    @GetMapping("{id}")
    public Object get(@PathVariable("id") long id) {
        return userRepository.getOne(id);
    }

    //register works
    @PostMapping("register")
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);

        Role role = new Role();
        role.setUsername(user.getUsername());
        role.setAuthority("ROLE_USER");
        user.setRole(role);
        userRepository.save(user);
    }

    //this works but not with my credentials? we are getting closer..
    @RequestMapping("login")
    public Principal user(Principal user) {
        return user;
    }

    //



}
