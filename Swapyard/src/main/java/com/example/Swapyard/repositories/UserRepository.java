package com.example.Swapyard.repositories;

import com.example.Swapyard.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository <User, Long> {

//    @Query("select a from Users a where a.username = :username and a.password = :password")
//    User findByUsernameAndPassword(@Param("email") String username,
//                              @Param("mobile") String password);
//}

    public User findByEmailAndPassword(String email, String password);
}
