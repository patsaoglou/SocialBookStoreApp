package com.SE2024.SocialBookStore.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.SE2024.SocialBookStore.dao.BookDAO;
import com.SE2024.SocialBookStore.model.Book;
import com.SE2024.SocialBookStore.model.BookAuthor;
import com.SE2024.SocialBookStore.model.BookCategory;

@Service
public class BookServiceValidator {

    BookDAO bookDAO;
    
    public BookServiceValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public Book validateBookExistance(int bookId){
        Book book = bookDAO.findById(bookId);
        if (book == null){
            throw new IllegalArgumentException("Book with bookId does not exist");
        }

        return book;
    }

    public void validateBookObject(Book bookData){
        validateBookTitle(bookData.getBookTitle());
        validateBookSummary(bookData.getBookSummary());
        
        validateBookAuthors(bookData.getAuthors());
        
        validateBookCategory(bookData.getBookCategory());

    }



    private void validateBookTitle(String bookTitle){
        if (bookTitle == null){
            throw new IllegalArgumentException("Book title is null");
        }
    }

    private void validateBookSummary(String bookSummary){
        if (bookSummary == null){
            throw new IllegalArgumentException("Book summary is null");
        }
    }

    private void validateBookAuthors(Set<BookAuthor> authors){
        if (authors == null){
            throw new IllegalArgumentException("Book authors is null");
        }
        for(BookAuthor author: authors){
            validateBookAuthor(author);
        }

    }

    private void validateBookAuthor(BookAuthor author){
        validateBookAuthorFirstName(author.getFirstName());
        validateBookAuthorLastName(author.getLastName());
    }

    private void validateBookAuthorFirstName(String firstName){
        if (firstName == null){
            throw new IllegalArgumentException("Author's first name is null");
        }
    }

    private void validateBookAuthorLastName(String lastName){
        if (lastName == null){
            throw new IllegalArgumentException("Author's last name is null");
        }
    }


    private void validateBookCategory(BookCategory bookCategory){
        if (bookCategory == null){
            throw new IllegalArgumentException("Book category is null");
            
        }else{
            validateBookCategoryName(bookCategory.getCategoryName());
        }
    }

    private void validateBookCategoryName(String categoryName){
        if (categoryName == null){
            throw new IllegalArgumentException("Book category name is null");
        }
    }

}
