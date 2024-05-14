package com.SE2024.SocialBookStore.dtos;

import java.util.Set;

public class UserProfileDTO {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private int age;
    private String telephone;
    private Set<BookAuthorDTO> favouriteBookAuthors;
    private Set<BookCategoryDTO> favouriteBookCategories;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public Set<BookAuthorDTO> getFavouriteBookAuthors() {
        return favouriteBookAuthors;
    }
    public void setFavouriteBookAuthors(Set<BookAuthorDTO> favouriteBookAuthors) {
        this.favouriteBookAuthors = favouriteBookAuthors;
    }
    public Set<BookCategoryDTO> getFavouriteBookCategories() {
        return favouriteBookCategories;
    }
    public void setFavouriteBookCategories(Set<BookCategoryDTO> favouriteBookCategories) {
        this.favouriteBookCategories = favouriteBookCategories;
    }

}
