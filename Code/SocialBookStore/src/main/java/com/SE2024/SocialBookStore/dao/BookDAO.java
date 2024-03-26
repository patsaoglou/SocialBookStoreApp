package com.SE2024.SocialBookStore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SE2024.SocialBookStore.model.Book;

@Repository
public interface BookDAO extends JpaRepository<Book, Integer> {

    public Book findById(int id);
    
}