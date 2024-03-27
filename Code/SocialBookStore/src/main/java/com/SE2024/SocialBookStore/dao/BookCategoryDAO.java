package com.SE2024.SocialBookStore.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SE2024.SocialBookStore.model.BookCategory;

@Repository
public interface BookCategoryDAO extends JpaRepository<BookCategory, Integer>{
    public BookCategory findById(int id);
    public BookCategory findByCategoryName(String name);
}