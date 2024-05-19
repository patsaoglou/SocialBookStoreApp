package com.SE2024.SocialBookStore.service.userProfile;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SE2024.SocialBookStore.dao.BookAuthorDAO;
import com.SE2024.SocialBookStore.dao.BookCategoryDAO;
import com.SE2024.SocialBookStore.dao.UserProfileDAO;
import com.SE2024.SocialBookStore.dtos.UserProfileDTO;
import com.SE2024.SocialBookStore.mappers.UserProfileMapper;
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

    public UserProfileServiceImpl(UserProfileDAO userProfileDAO, BookCategoryDAO bookCategoryDAO,
            BookAuthorDAO bookAuthorDAO) {
        this.userProfileDAO = userProfileDAO;
        this.bookCategoryDAO = bookCategoryDAO;
        this.bookAuthorDAO = bookAuthorDAO;
    }

    public UserProfileDTO registerUserProfileData(UserProfileDTO userProfile){
        UserProfileServiceValidator validator = new UserProfileServiceValidator(userProfileDAO);
        validator.validateUserProfileObject(UserProfileMapper.toEntity(userProfile));
        UserProfile newUser = userProfileDAO.save(UserProfileMapper.toEntity(userProfile));
        
        return UserProfileMapper.toDTO(userProfileDAO.findByUsername(newUser.getUsername()));        
    }
    
    public UserProfileDTO retrieveUserProfile(String username){
        UserProfileServiceValidator validator = new UserProfileServiceValidator(userProfileDAO);
        UserProfile user = validator.validateUsername(username);

        return UserProfileMapper.toDTO(user);        
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
