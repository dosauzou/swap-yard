package com.example.Swapyard.repositories;

import com.example.Swapyard.models.Swap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SwapRepository extends JpaRepository<Swap, Long> {
}
