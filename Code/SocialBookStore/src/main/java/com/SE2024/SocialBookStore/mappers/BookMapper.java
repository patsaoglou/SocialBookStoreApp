package com.SE2024.SocialBookStore.mappers;

import java.util.stream.Collectors;

import com.SE2024.SocialBookStore.dtos.BookDTO;
import com.SE2024.SocialBookStore.model.Book;

public class BookMapper {
        
    public static BookDTO toDTO(Book book) {
            BookDTO bookDTO = new BookDTO();
            
            bookDTO.setId(book.getId());
            bookDTO.setBookTitle(book.getBookTitle());
            bookDTO.setBookSummary(book.getBookSummary());
            bookDTO.setAuthors(book.getAuthors().stream()
                .map(BookAuthorMapper::toDTO)
                .collect(Collectors.toSet()));
                bookDTO.setBookCategory(BookCategoryMapper.toDTO(book.getBookCategory()));
            
            return bookDTO;
    }

    public static Book toEntity(BookDTO bookDTO){
        Book bookEntity = new Book();
        
        bookEntity.setId(bookDTO.getId());
        bookEntity.setBookTitle(bookDTO.getBookTitle());
        bookEntity.setBookSummary(bookDTO.getBookSummary());
        bookEntity.setBookCategory(BookCategoryMapper.toEntity(bookDTO.getBookCategory()));
        
        return bookEntity;
    }

}

