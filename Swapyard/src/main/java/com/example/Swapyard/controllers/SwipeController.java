package com.example.Swapyard.controllers;

import com.example.Swapyard.models.Items;
import com.example.Swapyard.models.Swipes;
import com.example.Swapyard.models.Users;
import com.example.Swapyard.repositories.ItemRepository;
import com.example.Swapyard.repositories.SwipeRepository;
import com.example.Swapyard.repositories.UserRepository;
import com.example.Swapyard.services.SwipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "https://localhost:4200", allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token"})
@RequestMapping("/api/v1/")
public class SwipeController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private SwipeRepository swipeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SwipeService swipeService;

    @RequestMapping("{userId}/swiped/{swipeId}")
    public Swipes createSwipe(@PathVariable ("swipeId") Long swipeId, @PathVariable ("userId") String username) throws IOException {
        //find
        Users user = userRepository.findByUsername(username);
        //set the swipeUserId = user.getId
        Items item = itemRepository.getById(swipeId);
        Swipes swipe = new Swipes(item);
        swipe.setUserId(user.getId());
        swipeRepository.save(swipe);

        swipeService.comparison(user);

       System.out.println(swipeId);

        return swipe;
    }




}
