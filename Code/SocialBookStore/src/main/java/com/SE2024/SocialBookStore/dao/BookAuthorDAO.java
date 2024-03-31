package com.SE2024.SocialBookStore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SE2024.SocialBookStore.model.BookAuthor;


@Repository
public interface BookAuthorDAO extends JpaRepository<BookAuthor, Integer>{

    public BookAuthor findById(int id);
    public BookAuthor findByFirstNameAndLastName(String firstName, String lastName);
}