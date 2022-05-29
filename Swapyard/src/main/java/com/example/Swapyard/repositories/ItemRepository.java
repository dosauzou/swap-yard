package com.example.Swapyard.repositories;

import com.example.Swapyard.models.Items;
import com.example.Swapyard.models.Swipes;
import com.example.Swapyard.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Items, Long>{

    List<Items>  findByUsers(@Param("users") Users users);


}
