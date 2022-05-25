package com.example.Swapyard.controllers;

//import com.example.Swapyard.models.CustomUserDetails;

import com.example.Swapyard.models.Items;
import com.example.Swapyard.models.Post;
import com.example.Swapyard.models.Role;
import com.example.Swapyard.models.Users;
import com.example.Swapyard.repositories.SwipeRepository;
import com.example.Swapyard.services.PostService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.Swapyard.repositories.UserRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "https://localhost:4200", allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token"})
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SwipeRepository s;

    @Autowired
    private PostService p;
    @GetMapping("users-list")
    public List<Users> list() {
        return userRepository.findAll();
    }


    @GetMapping("{id}")
    public Object get(@PathVariable("id") String id) {
        return userRepository.findByUsername(id);
    }

    @PostMapping("dislike/{userId}")
    public Object postDislike(@RequestBody Long idr, @PathVariable ("userId") String username) {
        Users user1 = userRepository.findByUsername(username);
        user1.getDislikes().add(idr);
        userRepository.save(user1);
        return null;
    }

    @PostMapping("profiler/{userId}")
    public Post uploadFile(@RequestParam("files") MultipartFile file, @PathVariable ("userId") String username) throws IOException {
        Users u = userRepository.findByUsername(username);
        Post x = p.createPost(file);
        u.setProfilepic(x);
        userRepository.save(u);
        return x;

    }

    @PostMapping("details/{userId}")
    public Object postDetails(@RequestBody String details, @PathVariable ("userId") String username) throws JSONException {
        Users user1 = userRepository.findByUsername(username);
        user1.setBio(details);
        userRepository.save(user1);


//        user1.getDislikes().add(idr);
//        userRepository.save(user1);
        return details;
    }
    @PostMapping("settings/{userId}")
    public Object postSettings(@RequestBody String details, @PathVariable ("userId") String username) throws JSONException {
      JSONObject o = new JSONObject(details);
        Users user1 = userRepository.findByUsername(username);

        if(o.has("password")){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(o.get("password").toString());
            System.out.println(encodedPassword);
            user1.setPassword(encodedPassword);
            userRepository.save(user1);

        }
        if(o.has("email")){
            user1.setEmail(o.get("email").toString());
            userRepository.save(user1);
        }

//        user1.getDislikes().add(idr);
//        userRepository.save(user1);
        return o;
    }

    @GetMapping("dislike/{userId}")
    public Object getAllLikesDisliked(@PathVariable ("userId") String username) {
        Users user1 = userRepository.findByUsername(username);
       List<Long> dislikes =  user1.getDislikes();
        List<Long> likes = s.findByUserId(user1.getId()).stream().map(p -> p.getItems().getId()).collect(Collectors.toList());
        List<Long> userItems = user1.getItems().stream().map((p-> p.getId())).collect(Collectors.toList());
        HashSet<Long> set = new HashSet<>();
        set.addAll(likes);
        set.addAll(dislikes);
        set.addAll(userItems);
        System.out.println(set);

        return set;
    }

    //register works
    @PostMapping("register")
    @ResponseStatus(HttpStatus.OK)
    public Users create(@RequestBody Users user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);

        Role role = new Role();
        role.setUsername(user.getUsername());
        role.setAuthority("ROLE_USER");
        user.setRole(role);
        userRepository.save(user);
        return user;
    }

    //this works but not with my credentials? we are getting closer..
    @RequestMapping("login")
    public Principal user(Principal user) throws IOException {

        return user;
    }

    //



}
