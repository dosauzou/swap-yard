package com.example.Swapyard.services;

import com.example.Swapyard.models.Post;
import com.example.Swapyard.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepo;

    public List<Post> storePostFromFile(MultipartFile[] file) throws IOException {
        List<Post> posts = new ArrayList<>();
        for(MultipartFile x : file) {
            String fileName = StringUtils.cleanPath(x.getOriginalFilename());
            Post dbFile = new Post(fileName, x.getContentType(), x.getBytes());
            posts.add(dbFile);
        }

        return posts;
    }

    public  Post createPost(MultipartFile file) throws IOException {

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Post dbFile = new Post(fileName, file.getContentType(), file.getBytes());

        return dbFile;
    }


    public void storePost(Post post){
        postRepo.save(post);
    }

    public Post getPost(String fileName){
       final Post post = postRepo.findByFileName(fileName);
       Post newPost = new Post(post.getFileName(), post.getFileType(), post.getData());
       return newPost;

    }

    public List<Post> findAll(){
        return postRepo.findAll();
    }
}
