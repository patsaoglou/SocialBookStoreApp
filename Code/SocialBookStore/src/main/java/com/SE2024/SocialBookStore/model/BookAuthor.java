package com.SE2024.SocialBookStore.model;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "bookAuthor")
public class BookAuthor {
    
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bookAuthorId")
    private int id;

    @Column(name="firstName")
	private String firstName;

    @Column(name="lastName")
	private String lastName;

    @ManyToMany
    @JoinTable(name = "authorWrote",
                joinColumns = @JoinColumn(name = "bookAuthorId", referencedColumnName = "bookAuthorId"),
                inverseJoinColumns = @JoinColumn(name = "bookId", referencedColumnName = "bookId"))
    private  Set<Book> authorWrote;


    public BookAuthor() {
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }    
}
