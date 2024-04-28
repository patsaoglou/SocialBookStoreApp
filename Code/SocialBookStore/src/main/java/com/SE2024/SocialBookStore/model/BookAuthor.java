package com.SE2024.SocialBookStore.model;

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

    public BookAuthor() {
    }

    public BookAuthor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

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
    
    public String toStringAuthorName(){
        return getFirstName()+" "+getLastName();
    }
}
