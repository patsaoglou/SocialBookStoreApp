package com.SE2024.SocialBookStore.model;

import java.util.Set;


import jakarta.persistence.*;

@Entity
@Table(name="books")
public class Book {
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bookId")
    private int id;

    @Column(name="title")
	private String bookTitle;

    @Column(name="summary")
    private String bookSummary;
    
    @ManyToMany
    @JoinTable(name = "authorWroteBook",
                joinColumns = @JoinColumn(name = "bookId", referencedColumnName = "bookId"),
                inverseJoinColumns = @JoinColumn(name = "bookAuthorId", referencedColumnName = "bookAuthorId"))
    private  Set<BookAuthor> authors;
    
    @ManyToOne
    @JoinTable(name = "bookIsCategory",
                joinColumns = @JoinColumn(name = "bookId", referencedColumnName = "bookId"),
                inverseJoinColumns = @JoinColumn(name = "bookCategoryId", referencedColumnName = "bookCategoryId"))
    private BookCategory bookCategory;

    public Book() {
    }

    public int getId() {
        return id;
    }

    public String getBookTitle() {
        return bookTitle;
    }


    public String getBookSummary() {
        return bookSummary;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setBookSummary(String bookSummary) {
        this.bookSummary = bookSummary;
    }

    public Set<BookAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<BookAuthor> authors) {
        this.authors = authors;
    }

    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String toStringAuthors(){
        String authorsString = "";
        int counter = 1;
        for (BookAuthor author: authors){
            authorsString += author.getFirstName()+" "+ author.getLastName();            
            if (authors.size()>1 && authors.size()>counter){
                authorsString +=", ";
            }

            counter++;
        }

        return authorsString;
    }

}
