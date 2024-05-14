package com.SE2024.SocialBookStore.mappers;

import com.SE2024.SocialBookStore.dtos.BookCategoryDTO;
import com.SE2024.SocialBookStore.model.BookCategory;

public class BookCategoryMapper {
    
    public static BookCategoryDTO toDTO(BookCategory bookCategory) {
        return new BookCategoryDTO(bookCategory.getCategoryName());
    }

    public static BookCategory toEntity(BookCategoryDTO bookCategoryDTO) {
        BookCategory category = new BookCategory();
        category.setCategoryName(bookCategoryDTO.getCategoryName());
        
        return category;
    }
}
