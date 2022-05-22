package com.example.Swapyard.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "userMatches")
public class UserMatches {

    @JsonBackReference
    @OneToOne(cascade = CascadeType.MERGE)
    Users match;
    private String chatId;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    @ManyToOne
    @JoinColumn(name = "swap_id")
    private Swap swap;

    public Swap getSwap() {
        return swap;
    }

    public void setSwap(Swap swap) {
        this.swap = swap;
    }

//Two has sets

    public Users getMatch() {
        return match;
    }

    public void setMatch(Users match) {
        this.match = match;
    }

    public UserMatches() {

    }

//    public Set<Users> getMatches() {
//        return matches;
//    }
//
//    public void setMatches(Set<Users> matches) {
//        this.matches = matches;
//    }




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserMatches(Set<Users> result) {

    }


}
