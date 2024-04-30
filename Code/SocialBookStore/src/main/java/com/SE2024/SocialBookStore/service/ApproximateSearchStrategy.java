package com.SE2024.SocialBookStore.service;

import java.util.List;

import com.SE2024.SocialBookStore.model.Book;
import com.SE2024.SocialBookStore.model.BookAuthor;

public class ApproximateSearchStrategy extends TemplateSearchStrategy{
    @Override
    public List<Book> makeInitialListOfBooks(String keyword) {
         return bookDao.findByBookTitleContaining(keyword);
    }

    @Override
    public boolean checkIfAuthorsMatch(List<BookAuthor> authors, Book book) {
        for (BookAuthor author: book.getAuthors()){
            if (authors.contains(author)){
                return true;
            }

        }
        return false;
    }
}
