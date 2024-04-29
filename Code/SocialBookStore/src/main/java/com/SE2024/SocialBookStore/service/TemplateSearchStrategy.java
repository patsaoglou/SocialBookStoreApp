package com.SE2024.SocialBookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.SE2024.SocialBookStore.dao.BookDAO;
import com.SE2024.SocialBookStore.dao.BookRequestDAO;
import com.SE2024.SocialBookStore.dao.UserProfileDAO;
import com.SE2024.SocialBookStore.dtos.BookAuthor;
import com.SE2024.SocialBookStore.model.Book;
import com.SE2024.SocialBookStore.model.BookCategory;

public abstract class TemplateSearchStrategy implements SearchStrategy {
    
    @Autowired
    BookDAO bookDao;

    @Autowired 
    BookRequestDAO bookRequestDAO;

    @Autowired
    UserProfileDAO userProfileDAO;

    // public List<Book> search(String keyword, int strategy, int recommendations){
    //     List<Book> initial_search_results = makeInitialListOfBooks(keyword);
    // }   

    public abstract List<Book> makeInitialListOfBooks(String keyword);
    public abstract boolean checkIfAuthorsMatch(String keyword);
    public abstract boolean checkIfCategoriesMatch(String keyword);



}
