package com.SE2024.SocialBookStore.dtos;


import com.SE2024.SocialBookStore.model.Book;

public class RegisterBookForm {
    private Book book;
    private String username;
    
    public RegisterBookForm() {
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Book getBook() {
        return book;
    }

    public String getUsername() {
        return username;
    }   

}
