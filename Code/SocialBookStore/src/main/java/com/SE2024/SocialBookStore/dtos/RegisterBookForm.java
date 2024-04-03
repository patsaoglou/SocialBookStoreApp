package com.SE2024.SocialBookStore.dtos;


import java.util.ArrayList;
import java.util.List;
import com.SE2024.SocialBookStore.model.BookCategory;

public class RegisterBookForm {
    private String bookTitle;
    private String bookSummary;
    private BookCategory bookCategory;
    private List<BookAuthor> authors;

    public String getBookTitle() {
        return bookTitle;
    }
    public String getBookSummary() {
        return bookSummary;
    }
    public BookCategory getCategory() {
        return bookCategory;
    }
    public List<BookAuthor> getAuthors() {
        return authors;
    }
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    public void setBookSummary(String bookSummary) {
        this.bookSummary = bookSummary;
    }
    public void setCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }
    public void setAuthors(List<BookAuthor> authors) {
        this.authors = authors;
    }
    public RegisterBookForm() {
        authors = new ArrayList<>();
    }

    
}
