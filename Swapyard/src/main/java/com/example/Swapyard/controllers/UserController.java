package com.example.Swapyard.controllers;

import com.example.Swapyard.models.CustomUserDetails;
import com.example.Swapyard.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.Swapyard.repositories.UserRepository;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/")
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

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody User user){
        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        customUserDetails.isEnabled();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }


    //this works but not with my credentials? we are getting closer..
    @RequestMapping ("/login")
    public Principal user(Principal user) {
        System.out.println(user);
        return user;
    }

    //


}
