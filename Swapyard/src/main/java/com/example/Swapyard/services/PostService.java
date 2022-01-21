package com.example.Swapyard.services;

import com.example.Swapyard.models.Post;
import com.example.Swapyard.models.User;
import com.example.Swapyard.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepo;

    public Post storePostFromFile(MultipartFile file) throws IOException {
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
