package com.SE2024.SocialBookStore.mappers;

import java.util.stream.Collectors;

import com.SE2024.SocialBookStore.dtos.UserProfileDTO;
import com.SE2024.SocialBookStore.model.UserProfile;

public class UserProfileMapper {
    
    public static UserProfileDTO toDTO(UserProfile userProfile){
        UserProfileDTO userDTO = new UserProfileDTO();

        userDTO.setId(userProfile.getId());
        userDTO.setUsername(userProfile.getUsername());
        userDTO.setFirstName(userProfile.getFirstName());
        userDTO.setLastName(userProfile.getLastName());
        userDTO.setAddress(userProfile.getAddress());
        userDTO.setAge(userProfile.getAge());
        userDTO.setTelephone(userProfile.getTelephone());

        userDTO.setFavouriteBookAuthors(userProfile.getFavouriteBookAuthors().stream()
            .map(BookAuthorMapper::toDTO)
            .collect(Collectors.toSet()));

        userDTO.setFavouriteBookCategories(userProfile.getFavouriteBookCategories().stream()
            .map(BookCategoryMapper::toDTO)
            .collect(Collectors.toSet()));

        return userDTO;
    }

    // this is only used on register so only user info are mapped to entity
    public static UserProfile toEntity(UserProfileDTO dto) {
        UserProfile userProfile = new UserProfile();
        
        userProfile.setId(dto.getId());
        userProfile.setUsername(dto.getUsername());
        userProfile.setFirstName(dto.getFirstName());
        userProfile.setLastName(dto.getLastName());
        userProfile.setAddress(dto.getAddress());
        userProfile.setAge(dto.getAge());
        userProfile.setTelephone(dto.getTelephone());

        return userProfile;
    }
}
