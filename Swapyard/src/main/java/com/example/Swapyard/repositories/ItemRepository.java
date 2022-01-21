package com.example.Swapyard.repositories;

import com.example.Swapyard.models.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Items, String>{

}
