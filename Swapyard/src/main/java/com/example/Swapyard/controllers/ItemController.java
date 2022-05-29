package com.example.Swapyard.controllers;

import com.example.Swapyard.models.Items;
import com.example.Swapyard.models.Swap;
import com.example.Swapyard.models.Users;
import com.example.Swapyard.repositories.ItemRepository;
import com.example.Swapyard.repositories.SwapRepository;
import com.example.Swapyard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "https://localhost:4200", allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token"})
@RequestMapping("/api/v1/")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SwapRepository swapRepository;

    @RequestMapping( "newItem/{username}")
    public Items createItem( @RequestBody Items item, @PathVariable("username") String username ){
        Users user = userRepository.findByUsername(username);
//        List <Items> postsList = new ArrayList<>();
        System.out.println(user.getUsername());
        item.setUsers(user);
        user.getItems().add(item);

//        postsList.add(item);
        System.out.println(user.getItems());
        userRepository.save(user);
        return item;
    }

    @GetMapping("getItems")
    public Object[] getItems(){

        //All items in the database
        List<Items>  items = itemRepository.findAll();
        //Items that have been swapped
        List <Swap> swaps = swapRepository.findAll();
        List<Items> swappedItems = new ArrayList<>();

        swaps.forEach(p-> swappedItems.addAll(p.getSwapItems()));
        Object[] itemsList ;

        HashSet<Items> finalItems =  new HashSet<>();
        finalItems.addAll(items);
        finalItems.removeAll(swappedItems);
        itemsList = finalItems.toArray();
        return itemsList;
    }
}
