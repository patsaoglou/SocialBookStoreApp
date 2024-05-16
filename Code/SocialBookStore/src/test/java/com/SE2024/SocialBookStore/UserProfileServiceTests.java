package com.SE2024.SocialBookStore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SE2024.SocialBookStore.dao.UserProfileDAO;
import com.SE2024.SocialBookStore.dtos.UserProfileDTO;
import com.SE2024.SocialBookStore.model.UserProfile;
import com.SE2024.SocialBookStore.service.userProfile.UserProfileServiceImpl;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class UserProfileServiceTests {

    @Autowired
    private UserProfileServiceImpl userProfileService;

    @Autowired
    private UserProfileDAO userProfileDAO;

    @Test
    public void HappyDayRegisterUserProfileData(){
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        
        userProfileDTO.setUsername("TestUser");
        userProfileDTO.setFirstName("TestFirstName");
        userProfileDTO.setLastName("TestLastName");
        userProfileDTO.setAddress("TestAddress 1");
        userProfileDTO.setAge(20);
        userProfileDTO.setTelephone("1234567890");

        UserProfileDTO newUser = userProfileService.registerUserProfileData(userProfileDTO);

        UserProfile newUserFromDAO = userProfileDAO.findByUsername("TestUser");

        assertEquals("TestUser", newUser.getUsername());
        assertEquals("TestFirstName", newUser.getFirstName());
        assertEquals("TestLastName", newUser.getLastName());
        assertEquals("TestAddress 1", newUser.getAddress());
        assertEquals(20, newUser.getAge());
        assertEquals("1234567890", newUser.getTelephone());

        assertNotEquals(0, newUserFromDAO.getId());
        assertEquals("TestUser", newUserFromDAO.getUsername());
        assertEquals("TestFirstName", newUserFromDAO.getFirstName());
        assertEquals("TestLastName", newUserFromDAO.getLastName());
        assertEquals("TestAddress 1", newUserFromDAO.getAddress());
        assertEquals(20, newUserFromDAO.getAge());
        assertEquals("1234567890", newUserFromDAO.getTelephone());
    }

    @Test
    public void UsernameExistsRegisterUserProfileData(){
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        
        userProfileDTO.setUsername("TestUser");
        userProfileDTO.setFirstName("TestFirstName");
        userProfileDTO.setLastName("TestLastName");
        userProfileDTO.setAddress("TestAddress 1");
        userProfileDTO.setAge(20);
        userProfileDTO.setTelephone("1234567890");

        userProfileService.registerUserProfileData(userProfileDTO);

        // Re-register user with same username
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userProfileService.registerUserProfileData(userProfileDTO);
        });

        assertEquals("Username already exists", exception.getMessage());

    }

    @Test
    public void UsernameNullRegisterUserProfileData(){
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        
        // Username not passed
        userProfileDTO.setFirstName("TestFirstName");
        userProfileDTO.setLastName("TestLastName");
        userProfileDTO.setAddress("TestAddress 1");
        userProfileDTO.setAge(20);
        userProfileDTO.setTelephone("1234567890");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userProfileService.registerUserProfileData(userProfileDTO);
        });

        assertEquals("Username is null", exception.getMessage());
    }

    @Test
    public void InvalidAgeRegisterUserProfileData(){
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        
        userProfileDTO.setUsername("TestUser");
        userProfileDTO.setFirstName("TestFirstName");
        userProfileDTO.setLastName("TestLastName");
        userProfileDTO.setAddress("TestAddress 1");
        userProfileDTO.setAge(-20);
        userProfileDTO.setTelephone("1234567890");
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userProfileService.registerUserProfileData(userProfileDTO);
        });

        assertEquals("Age must be >0", exception.getMessage());

    }

    @Test
    public void UserNameExistsRegisterUserProfileData(){
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        
        userProfileDTO.setUsername("TestUser");
        userProfileDTO.setFirstName("TestFirstName");
        userProfileDTO.setLastName("TestLastName");
        userProfileDTO.setAddress("TestAddress 1");
        userProfileDTO.setAge(20);
        userProfileDTO.setTelephone("1234567890");
        
        userProfileService.registerUserProfileData(userProfileDTO);

        UserProfileDTO userProfileDTO1 = new UserProfileDTO();
        
        userProfileDTO1.setUsername("TestUser");
        userProfileDTO1.setFirstName("TestFirstName2");
        userProfileDTO1.setLastName("TestLastName2");
        userProfileDTO1.setAddress("TestAddress 12");
        userProfileDTO1.setAge(20);
        userProfileDTO1.setTelephone("1234567890");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userProfileService.registerUserProfileData(userProfileDTO1);
        });

        assertEquals("Username already exists", exception.getMessage());
    }
    
    @Test
    public void HappyDayRetrieveUserProfile(){
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        
        userProfileDTO.setUsername("TestUser");
        userProfileDTO.setFirstName("TestFirstName");
        userProfileDTO.setLastName("TestLastName");
        userProfileDTO.setAddress("TestAddress 1");
        userProfileDTO.setAge(20);
        userProfileDTO.setTelephone("1234567890");

        UserProfileDTO newUser = userProfileService.registerUserProfileData(userProfileDTO);

        UserProfileDTO retrievedUser  = userProfileService.retrieveUserProfile("TestUser");

        assertEquals(retrievedUser.getUsername(), newUser.getUsername());
        assertEquals(retrievedUser.getFirstName(), newUser.getFirstName());
        assertEquals(retrievedUser.getLastName(), newUser.getLastName());
        assertEquals(retrievedUser.getAddress(), newUser.getAddress());
        assertEquals(retrievedUser.getAge(), newUser.getAge());
        assertEquals(retrievedUser.getTelephone(), newUser.getTelephone());
        assertNotEquals(retrievedUser.getFavouriteBookAuthors(), null);
        assertNotEquals(retrievedUser.getFavouriteBookCategories(), null);
    }

    @Test
    public void UserDoesNotExistRetrieveUserProfile(){
        // Random Username without adding new user
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userProfileService.retrieveUserProfile("TestUser");
        });

        assertEquals("Username doesn't exists", exception.getMessage());

    }

    @Test
    public void HappyDayAddFavouriteAuthor(){
        
    }
}
