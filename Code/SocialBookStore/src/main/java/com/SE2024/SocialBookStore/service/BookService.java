package com.SE2024.SocialBookStore.service;

import java.util.List;

import com.SE2024.SocialBookStore.model.Book;

public interface BookService {

    List<Book> retrieveBookOffers(String username);
    Book addBookOffer(Book bookData, String authors, String username);
    void deleteBookOffer(String username, int bookId);

    Book getBookById(int bookId);
    List<Book> showAvailableBooksToUser(String username);

    List<Book> searchBooks(String keyword, int strategy, int recommendations);
    
} 