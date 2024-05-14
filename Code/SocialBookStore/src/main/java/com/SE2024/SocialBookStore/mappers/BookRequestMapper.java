package com.SE2024.SocialBookStore.mappers;

import com.SE2024.SocialBookStore.dtos.BookRequestDTO;
import com.SE2024.SocialBookStore.model.BookRequest;

public class BookRequestMapper {
    
    public static BookRequestDTO toDTO(BookRequest bookRequest) {
        
        BookRequestDTO requestDTO = new BookRequestDTO();
        requestDTO.setRequestId(bookRequest.getRequestId());
        requestDTO.setRequestedBook(BookMapper.toDTO(bookRequest.getRequestedBook()));
        requestDTO.setRequester(UserProfileMapper.toDTO(bookRequest.getRequester()));
        requestDTO.setStatus(bookRequest.getStatus());
        
        return requestDTO;
    }
}
