package com.example.Swapyard.models;

public class MultiMap {
    Object user;
    Object items;
    Object swap;



    public MultiMap(Object user, Object items, Object swap) {
        this.user = user;
        this.items = items;
        this.swap = swap;
    }

    public Object getSwap() {
        return swap;
    }

    public void setSwap(Object swap) {
        this.swap = swap;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public Object getItems() {
        return items;
    }

    public void setItems(Object items) {
        this.items = items;
    }
}