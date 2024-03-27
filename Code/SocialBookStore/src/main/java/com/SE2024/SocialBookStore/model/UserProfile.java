package com.SE2024.SocialBookStore.model;


import java.util.*;

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
               joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userProfileId"),
               inverseJoinColumns = @JoinColumn(name = "bookId", referencedColumnName = "bookId"))
    private List <Book> bookOffers;

    @ManyToMany
    @JoinTable(name = "authorPreferations",
                joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userProfileId"),
                inverseJoinColumns = @JoinColumn(name = "bookAuthorId", referencedColumnName = "bookAuthorId"))
    private  Set<BookAuthor> favouriteBookAuthors;

    @ManyToMany
    @JoinTable(name = "categoryPreferations",
                joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userProfileId"),
                inverseJoinColumns = @JoinColumn(name = "bookCategoryId", referencedColumnName = "bookCategoryId"))
    private  Set<BookCategory> favouriteBookCategories;

    public UserProfile() {
    }

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
