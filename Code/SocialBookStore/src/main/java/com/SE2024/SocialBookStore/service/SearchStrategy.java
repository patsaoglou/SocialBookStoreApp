package com.SE2024.SocialBookStore.service;

import java.util.List;

import com.SE2024.SocialBookStore.dao.BookDAO;
import com.SE2024.SocialBookStore.dtos.BookAuthor;
import com.SE2024.SocialBookStore.model.Book;
import com.SE2024.SocialBookStore.model.BookCategory;

public interface SearchStrategy {
    List<Book> search(String keyword, int strategy, int recommendations, List<BookAuthor> favoriteAuthors, List<BookCategory> favoriteCategories, BookDAO bookDao);    
}
