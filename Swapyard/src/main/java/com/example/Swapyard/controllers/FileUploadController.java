package com.example.Swapyard.controllers;

import com.example.Swapyard.models.Post;
import com.example.Swapyard.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://localhost:4200", allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token"})
@RequestMapping("/api/v1/post/")
public class FileUploadController {

    @Autowired
    private PostService postService;

    @PostMapping("upload")
    public List<Post> uploadFile(@RequestParam("files") MultipartFile[] files) throws IOException {
        System.out.println(files);
        for(MultipartFile x: files){
            System.out.println(x.getOriginalFilename());
        }
        List<Post> fileName = postService.storePostFromFile(files);
//        System.out.println(fileName.getFileName());
        return fileName;

    }

//    @PostMapping("upload")
//    public Post uploadMultipleFile(@RequestParam("file") List<MultipartFile> file) throws IOException {
//        Post fileName = postService.storePostFromFile(file);
//        System.out.println(fileName.getFileName());
//        return fileName;
//
//    }

    @PostMapping("uploadRaw")
    public void storeRawPost (@RequestBody Post post){
        postService.storePost(post);
    }

}
