package com.example.Swapyard.repositories;

import com.example.Swapyard.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository <User, Long> {

    User findByUsername(@Param("username") String username);
    User findByEmailAndPassword(String email, String password);

}
