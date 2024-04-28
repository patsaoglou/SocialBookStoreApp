package com.SE2024.SocialBookStore.service;

import com.SE2024.SocialBookStore.model.UserProfile;

public interface UserProfileService {
    
    public UserProfile retrieveUserProfile(String username); //ok
    public UserProfile registerUserProfileData(UserProfile profileData); //ok
    public void addFavouriteAuthor(String authorName, String username);
    public void addFavouriteCategory(String categoryName, String username);

} 
