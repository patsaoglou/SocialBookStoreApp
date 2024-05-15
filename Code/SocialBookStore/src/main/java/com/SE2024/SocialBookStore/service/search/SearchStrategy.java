package com.SE2024.SocialBookStore.service.search;

import java.util.List;

import com.SE2024.SocialBookStore.dao.BookDAO;
import com.SE2024.SocialBookStore.model.BookAuthor;
import com.SE2024.SocialBookStore.model.Book;

public interface SearchStrategy {
    List<Book> search(String keyword, List<BookAuthor> authors,  BookDAO bookDao);    
}
