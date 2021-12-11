package com.example.Swapyard.repositories;

import com.example.Swapyard.models.Post;
import com.example.Swapyard.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
    public Post findByFileName(String fileName);

}
