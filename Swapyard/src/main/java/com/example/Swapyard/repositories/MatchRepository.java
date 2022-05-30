package com.example.Swapyard.repositories;

import com.example.Swapyard.models.UserMatches;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<UserMatches, Long> {
    List<UserMatches> findAllByChatId(String chatId);
    List<UserMatches> findByChatIdOrderByIdDesc(String chatId);
    List<UserMatches> findByChatIdAndChatIdOrderById(String chatId,String chat);


    List<UserMatches> findAllBySwapId(Long match);
}
