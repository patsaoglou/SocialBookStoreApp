package com.SE2024.SocialBookStore.dtos;

public class BookAuthorDTO {
    private String firstName;
    private String lastName;
        
    public BookAuthorDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
    public String toStringAuthorName(){
        return getFirstName()+" "+getLastName();
    }
}
