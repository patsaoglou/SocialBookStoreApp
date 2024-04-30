package com.SE2024.SocialBookStore.service;

import java.util.List;

import com.SE2024.SocialBookStore.model.BookAuthor;
import com.SE2024.SocialBookStore.model.Book;

public class ExactSearchStrategy extends TemplateSearchStrategy{


    @Override
    public List<Book> makeInitialListOfBooks(String keyword) {
         return bookDao.findByBookTitle(keyword);
    }

    @Override
    public boolean checkIfAuthorsMatch(List<BookAuthor> authors, Book book) {
        for (BookAuthor author: book.getAuthors()){
            if (!authors.contains(author)){
                return false;
            }

        }
        return true;
    }
    
}
