package com.example.Swapyard.controllers;

import com.example.Swapyard.models.Items;
import com.example.Swapyard.models.User;
import com.example.Swapyard.repositories.ItemRepository;
import com.example.Swapyard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token"})
@RequestMapping("/api/v1/")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping( "newItem/{username}")
    public Items createItem( @RequestBody Items item, @PathVariable("username") String username ){
        User user = userRepository.findByUsername(username);
        List <Items> postsList = new ArrayList<>();

        user.getItems().add(item);
        postsList.add(item);
        System.out.println(user.getItems());
        userRepository.save(user);
        return item;
    }
}
