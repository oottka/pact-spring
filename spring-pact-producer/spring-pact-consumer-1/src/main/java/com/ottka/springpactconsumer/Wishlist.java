package com.ottka.springpactconsumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Wishlist {
    private final long id;
    private final String owner;
    private final List<String> wishes;
    private boolean isNice;

    public Wishlist(long id, String owner, List<String> wishes, boolean isNice) {
        this.id = id;
        this.owner = owner;
        this.wishes = wishes;
        this.isNice = isNice;
    }

    public long getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public List<String> getWishes() {
        return wishes;
    }

    public boolean isNice() {
        return isNice;
    }
}
