package com.SE2024.SocialBookStore.model;

import java.util.List;

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

}
