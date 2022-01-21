package com.example.Swapyard.controllers;

import com.example.Swapyard.models.Post;
import com.example.Swapyard.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1/post")
public class FileUploadController {

    @Autowired
    private PostService postService;

    @PostMapping("upload")
    public Post uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        Post fileName = postService.storePostFromFile(file);
        System.out.println(fileName.getFileName());
        return fileName;

    }

    @PostMapping("uploadRaw")
    public void storeRawPost (@RequestBody Post post){
        postService.storePost(post);
    }

}
