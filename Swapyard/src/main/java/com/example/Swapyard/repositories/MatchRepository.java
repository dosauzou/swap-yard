package com.example.Swapyard.repositories;

import com.example.Swapyard.models.Swipes;
import com.example.Swapyard.models.UserMatches;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<UserMatches, Long> {
}
