package com.SE2024.SocialBookStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SE2024.SocialBookStore.dao.BookDAO;
import com.SE2024.SocialBookStore.model.Book;

@Service
public class BookSeviceImpl implements BookService{

    @Autowired
    BookDAO bookRepository;

    @Override
    public void addBookOffer(Book book) {
        bookRepository.save(book);
    }
    
}
