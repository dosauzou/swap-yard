package com.example.Swapyard.models;

public class MultiMap {
    Object user;
    Object items;
    Swap swap;
    String chatId;
    Object matchItems;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public MultiMap(Object user, Object items, Swap swap, String chatId, Object matchItems) {
        this.user = user;
        this.items = items;
        this.swap = swap;
        this.chatId = chatId;
        this.matchItems = matchItems;
    }

    public Object getMatchItems() {
        return matchItems;
    }

    public void setMatchItems(Object matchItems) {
        this.matchItems = matchItems;
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