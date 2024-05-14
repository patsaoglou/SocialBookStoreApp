package com.SE2024.SocialBookStore.dtos;

import java.util.Set;

public class BookDTO {
    private int id;
    private String bookTitle;
    private String bookSummary;
    private Set<BookAuthorDTO> authors;
    private BookCategoryDTO bookCategory;

    private String authorsFromForm;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getBookTitle() {
        return bookTitle;
    }
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    public String getBookSummary() {
        return bookSummary;
    }
    public void setBookSummary(String bookSummary) {
        this.bookSummary = bookSummary;
    }
    public Set<BookAuthorDTO> getAuthors() {
        return authors;
    }
    public void setAuthors(Set<BookAuthorDTO> authors) {
        this.authors = authors;
    }
    public BookCategoryDTO getBookCategory() {
        return bookCategory;
    }
    public void setBookCategory(BookCategoryDTO bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String toStringAuthors(){
        String authorsString = "";
        int counter = 1;
        
        for (BookAuthorDTO author: authors){
            authorsString += author.getFirstName()+" "+ author.getLastName();            
            if (authors.size()>1 && authors.size()>counter){
                authorsString +=", ";
            }

            counter++;
        }

        return authorsString;
    }
    public String getAuthorsFromForm() {
        return authorsFromForm;
    }
    public void setAuthorsFromForm(String authorsFromForm) {
        this.authorsFromForm = authorsFromForm;
    }
}
