package com.SE2024.SocialBookStore.service;

import java.util.List;

import com.SE2024.SocialBookStore.model.Book;
import com.SE2024.SocialBookStore.model.UserProfile;

public interface UserProfileService {
    
    // public UserProfile retrieveuUserProfile(String username);
    public UserProfile registerUserProfileData(UserProfile profileData);
    // public UserProfile changeUserProfileData(String username, UserProfile profileDataChanges);
    
    // List<Book> retrieveBookOffers(String username);
    // Book addBookOffer(String username, Book bookData);

    // List<Book> searchBooks();
    // List<Book> recommendBooks(String username);
    
    // void requestBook(int bookId, String username);
      
    // List<Book> retrieveBookRequests(String username);
    // List<UserProfile> retrieveRequestingUsers(int bookId);

    // void deleteBookOffer(String UserName, int bookId);
    // void deleteBookRequest(String Username, int bookId);
    
} 
