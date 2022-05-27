package com.example.Swapyard.models;

public class MultiMap {
    Object user;
    Object items;
    Swap swap;
    String chatId;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public MultiMap(Object user, Object items, Swap swap, String chatId) {
        this.user = user;
        this.items = items;
        this.swap = swap;
        this.chatId = chatId;
    }

    public Swap getSwap() {
        return swap;
    }

    public void setSwap(Swap swap) {
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