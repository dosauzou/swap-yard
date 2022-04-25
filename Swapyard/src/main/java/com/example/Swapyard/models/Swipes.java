package com.example.Swapyard.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import javax.persistence.*;

@Entity
public class Swipes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Long userId;

    //who Liked it?
    //what they liked
    public Swipes() {

    }

    public Swipes(Items items) {
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(optional = false)
    @JsonBackReference
    private Items items;

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    //User who swiped
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
