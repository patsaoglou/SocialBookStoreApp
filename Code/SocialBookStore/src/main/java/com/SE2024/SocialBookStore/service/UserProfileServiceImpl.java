package com.SE2024.SocialBookStore.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SE2024.SocialBookStore.dao.BookAuthorDAO;
import com.SE2024.SocialBookStore.dao.BookCategoryDAO;
import com.SE2024.SocialBookStore.dao.UserProfileDAO;
import com.SE2024.SocialBookStore.model.BookAuthor;
import com.SE2024.SocialBookStore.model.BookCategory;
import com.SE2024.SocialBookStore.model.UserProfile;

@Service
public class UserProfileServiceImpl implements UserProfileService{
    
    @Autowired
    UserProfileDAO userProfileDAO;
    
    @Autowired
    BookCategoryDAO bookCategoryDAO;
    
    @Autowired
    BookAuthorDAO bookAuthorDAO;
    
    public UserProfile registerUserProfileData(UserProfile profileData){
        UserProfileServiceValidator validator = new UserProfileServiceValidator(userProfileDAO);
        validator.validateUserProfileObject(profileData);
        userProfileDAO.save(profileData);

        return userProfileDAO.findByUsername(profileData.getUsername());        
    }
    
    public UserProfile retrieveUserProfile(String username){
        UserProfileServiceValidator validator = new UserProfileServiceValidator(userProfileDAO);
        UserProfile user = validator.validateUsername(username);

        return user;        
    }

    public void addFavouriteAuthor(String authorName, String username){
        if (authorName == null || authorName.isEmpty() ){
            return;
        }
        UserProfile profile = userProfileDAO.findByUsername(username);

        List<String> authorFirstAndLastName = Arrays.asList(authorName.split(" "));
        BookAuthor authorFromDAO = bookAuthorDAO.findByFirstNameAndLastName(authorFirstAndLastName.get(0), (authorFirstAndLastName.get(1)));
        
        if ( authorFromDAO == null){
            authorFromDAO = bookAuthorDAO.save(new BookAuthor(authorFirstAndLastName.get(0), (authorFirstAndLastName.get(1))));

        }

        profile.addFavouriteBookAuthor(authorFromDAO);
        userProfileDAO.save(profile);
    }

    public void addFavouriteCategory(String categoryName, String username){

        if (categoryName == null || categoryName.isEmpty() ){
            return;
        }
        UserProfile profile = userProfileDAO.findByUsername(username);

        BookCategory categoryFromDAO = bookCategoryDAO.findByCategoryName(categoryName);
        
        if ( categoryFromDAO == null){
            categoryFromDAO = bookCategoryDAO.save(new BookCategory(categoryName));

        }

        profile.addFavouriteBookCategory(categoryFromDAO);
        userProfileDAO.save(profile);
    }



}
