package com.SE2024.SocialBookStore.service.books;

import java.util.List;

import com.SE2024.SocialBookStore.dtos.BookRequestDTO;

public interface BookRequestService {
    
    void requestBook(int bookId, String username);
    void deleteBookRequest(int bookId, String username);

    List<BookRequestDTO> retrieveBookRequests(String username);
    List<BookRequestDTO> retrieveRequestingUsers(int bookId);

    void selectRequester(int requestId);

}
