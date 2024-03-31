package com.SE2024.SocialBookStore.dtos;

public class BookRequestForm {
    private int bookId;
    private String username;
    public int getBookId() {
        return bookId;
    }
    public String getUsername() {
        return username;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public void setUsername(String username) {
        this.username = username;
    }

}
