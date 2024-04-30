package com.SE2024.SocialBookStore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SE2024.SocialBookStore.model.Book;
import com.SE2024.SocialBookStore.model.BookAuthor;
import com.SE2024.SocialBookStore.model.BookCategory;

@Repository
public interface BookDAO extends JpaRepository<Book, Integer> {
    public Book findById(int id);
    public List<Book> findByBookTitle(String bookTitle);
    public List<Book> findByBookTitleContaining(String bookTitle);

    public List<Book> findByBookCategory(BookCategory category);
    public List<Book> findByAuthors(BookAuthor author);

    
}
