package com.SE2024.SocialBookStore.service;

import java.util.List;

import com.SE2024.SocialBookStore.model.BookRequest;

public interface BookRequestService {
    
    void requestBook(int bookId, String username);
    void deleteBookRequest(int bookId, String username);

    List<BookRequest> retrieveBookRequests(String username);
    List<BookRequest> retrieveRequestingUsers(int bookId);

}
