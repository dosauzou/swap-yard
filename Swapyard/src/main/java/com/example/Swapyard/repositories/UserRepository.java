package com.example.Swapyard.repositories;

import com.example.Swapyard.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository <Users, Long> {

    Users findByUsername(@Param("username") String username);
    Users findByEmailAndPassword(String email, String password);

}
