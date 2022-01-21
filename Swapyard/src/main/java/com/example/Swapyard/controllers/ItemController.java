package com.example.Swapyard.controllers;

import com.example.Swapyard.models.Item;
import com.example.Swapyard.models.User;
import com.example.Swapyard.repositories.ItemRepository;
import com.example.Swapyard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token"})
@RequestMapping("/api/v1/")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping( "newItem/{username}")
    public User createItem(@PathVariable("username")String username){

        User user = userRepository.findByUsername(username);
//        user.getItems().add(item);
//        System.out.println(item.getMaterial());
        return user;
    }
}
