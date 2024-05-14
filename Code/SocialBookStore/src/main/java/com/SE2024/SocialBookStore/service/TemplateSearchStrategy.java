package com.SE2024.SocialBookStore.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.SE2024.SocialBookStore.dao.BookDAO;
import com.SE2024.SocialBookStore.model.BookAuthor;
import com.SE2024.SocialBookStore.model.Book;

public abstract class TemplateSearchStrategy implements SearchStrategy {
    
    @Autowired
    BookDAO bookDao;   
    
    public List<Book> search(String keyword, List<BookAuthor> authors, BookDAO bookDao){
        this.bookDao = bookDao;
        List<Book> initial_search_results = new ArrayList<>();

        if (authors == null){
            return null;
        }
        
        initial_search_results = makeInitialListOfBooks(keyword);

        Iterator<Book> iterator = initial_search_results.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
        
            if (!checkIfAuthorsMatch(authors, book)) {
                iterator.remove();
            }
        }
        return initial_search_results;
    }   

    public abstract List<Book> makeInitialListOfBooks(String keyword);
    public abstract boolean checkIfAuthorsMatch(List<BookAuthor> authors, Book book);
}
