package com.SE2024.SocialBookStore.dtos;

public class DeleteBookForm {
    private String username;
    private int bookId;
    public String getUsername() {
        return username;
    }
    public int getBookId() {
        return bookId;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public DeleteBookForm() {
    }

    
}
