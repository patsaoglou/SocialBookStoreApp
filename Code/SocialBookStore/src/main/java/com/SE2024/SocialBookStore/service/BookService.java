package com.SE2024.SocialBookStore.service;

import java.util.List;

import com.SE2024.SocialBookStore.model.Book;

public interface BookService {

    List<Book> retrieveBookOffers(String username); // ok
    Book addBookOffer(Book bookData, String username); // ok 
    void deleteBookOffer(String username, int bookId); //ok

    // added
    Book getBookById(int bookId);
    
} 