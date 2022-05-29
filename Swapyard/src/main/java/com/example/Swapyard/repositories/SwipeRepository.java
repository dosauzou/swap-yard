package com.example.Swapyard.repositories;

import com.example.Swapyard.models.Items;
import com.example.Swapyard.models.Swipes;
import com.example.Swapyard.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SwipeRepository extends JpaRepository<Swipes, Long> {
    List<Swipes> findByUserId(Long userId);

}
