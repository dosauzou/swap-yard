package com.example.Swapyard.controllers;

import com.example.Swapyard.models.Post;
import com.example.Swapyard.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1/post")
public class FileDownloadController {

    @Autowired
    private PostService postService;

    @GetMapping("download/{fileName}")
    public Post downloadFile(@PathVariable String fileName){
        Post databaseFile = postService.getPost(fileName);
        return databaseFile;
    }

    //Create a find by user id method
    @GetMapping("downloads/{userId}")
    public List<Post> findAll(@PathVariable("userId") String userId){
        List posts = postService.findAll();
        return posts;
    }
}
