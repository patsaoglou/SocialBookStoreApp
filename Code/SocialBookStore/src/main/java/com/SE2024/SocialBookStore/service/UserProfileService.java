package com.SE2024.SocialBookStore.service;

import java.util.List;

import com.SE2024.SocialBookStore.model.Book;
import com.SE2024.SocialBookStore.model.UserProfile;

public interface UserProfileService {
    
    public UserProfile retrieveUserProfile(String username); //ok
    public UserProfile registerUserProfileData(UserProfile profileData); //ok
    // public UserProfile changeUserProfileData(String username, UserProfile profileDataChanges);
    
    List<Book> retrieveBookOffers(String username); // ok
    Book addBookOffer(Book bookData, String username); // ok 

    // List<Book> search();
    // List<Book> recommendBooks(String username);
    
    // void requestBook(int bookId, String username);
      
    // List<Book> retrieveBookRequests(String username);
    // List<UserProfile> retrieveRequestingUsers(int bookId);

    void deleteBookOffer(String username, int bookId); //ok
    // void deleteBookRequest(String username, int bookId);
    
} 
