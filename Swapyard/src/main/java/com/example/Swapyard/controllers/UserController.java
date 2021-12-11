package com.example.Swapyard.controllers;

import com.example.Swapyard.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.Swapyard.repositories.UserRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("users-list")
    public List<User> list(){
    return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Object get (@PathVariable("id") long id){
        return userRepository.getOne(id);
    }

    @PostMapping("save-user")
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody User user){
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
//        userRepository.save(user);
    }

    @PostMapping("login")
    public User loginUser(@RequestBody User user) throws Exception {
        String tempEmailId = user.getEmail();
        String tempPass = user.getPassword();
        User userObj = null;
        if(tempEmailId !=null && tempPass !=null){
          userObj =  userRepository.findByEmailAndPassword(tempEmailId, tempPass);
        }
        if (userObj ==null){
            throw new Exception("Bad credentials");
        }
        return userObj;
    }






}
