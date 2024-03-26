package com.SE2024.SocialBookStore.model;


import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="profiles")
public class UserProfile {
    
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userProfileId")
    private int id;

    @Column(name="username")
	private String username;

    @Column(name="firstName")
	private String firstName;

    @Column(name="lastName")
	private String lastName;

    @Column(name="address")
	private String address;

    @Column(name="age")
	private int age;

    @Column(name="telephone")
	private String telephone;
    
    @OneToMany
    @JoinTable(name = "bookOffers",
               joinColumns = @JoinColumn(name = "userId"),
               inverseJoinColumns = @JoinColumn(name = "bookId"))
    private List <Book> bookOffers;


    @ManyToMany
    @JoinTable(name = "authorPreferations",
                joinColumns = @JoinColumn(name = "userId"),
                inverseJoinColumns = @JoinColumn(name = "bookAuthorId"))
    private List <BookAuthor> favouriteBookAuthors;


    public UserProfile() {
    }

    // Todo
    // List of book categories
    // List of favorite authors
    // Make authors, category class

	public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public String getTelephone() {
        return telephone;
    } 

}
