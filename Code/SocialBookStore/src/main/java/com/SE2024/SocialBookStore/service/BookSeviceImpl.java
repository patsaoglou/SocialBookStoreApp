package com.SE2024.SocialBookStore.service;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SE2024.SocialBookStore.dao.BookAuthorDAO;
import com.SE2024.SocialBookStore.dao.BookCategoryDAO;
import com.SE2024.SocialBookStore.dao.BookDAO;
import com.SE2024.SocialBookStore.dao.BookRequestDAO;
import com.SE2024.SocialBookStore.dao.UserProfileDAO;
import com.SE2024.SocialBookStore.model.Book;
import com.SE2024.SocialBookStore.model.BookAuthor;
import com.SE2024.SocialBookStore.model.BookCategory;
import com.SE2024.SocialBookStore.model.UserProfile;

@Service
public class BookSeviceImpl implements BookService{


    private static final Logger logger = LoggerFactory.getLogger(BookSeviceImpl.class);

    @Autowired
    BookDAO bookDAO;
    
    @Autowired
    BookAuthorDAO bookAuthorDAO;

    @Autowired
    BookCategoryDAO bookCategoryDAO;

    @Autowired
    UserProfileDAO userProfileDAO;
    
    @Autowired
    BookRequestDAO bookRequestDAO;

    public Book addBookOffer(Book bookData, String authors, String username){

        List<String> authorList = Arrays.asList(authors.split(","));
        Set<BookAuthor> bookAuthors = new HashSet();

        for (String author : authorList) {
            List<String> authorFirstAndLastName = Arrays.asList(author.split(" "));
            BookAuthor bookAuthor = new BookAuthor(authorFirstAndLastName.get(0), authorFirstAndLastName.get(1));
            bookAuthors.add(bookAuthor);
        }

        bookData.setAuthors(bookAuthors);

        UserProfileServiceValidator userValidator = new UserProfileServiceValidator(userProfileDAO);
        BookServiceValidator bookValidator = new BookServiceValidator(bookDAO);    
        userValidator.validateUsername(username);
        bookValidator.validateBookObject(bookData);

        bookData.setAuthors(addAuthors(bookData.getAuthors()));
        bookData.setBookCategory(addBookCategory(bookData.getBookCategory()));
        
        Book newBook = bookDAO.save(bookData);
        addBookToUser(newBook, username);

        return newBook;
    }

    private BookCategory addBookCategory(BookCategory bookCategory){
        BookCategory bookCategoryFromDAO = bookCategoryDAO.findByCategoryName(bookCategory.getCategoryName());
        if ( bookCategoryFromDAO == null){
            bookCategoryDAO.save(bookCategory);
            bookCategoryFromDAO = bookCategoryDAO.findByCategoryName(bookCategory.getCategoryName());
        }
        return bookCategoryFromDAO;

    }

    private Set<BookAuthor> addAuthors(Set<BookAuthor> authors){
        Set<BookAuthor> authorsFromDAO = new HashSet<>();
        for (BookAuthor author: authors){
            BookAuthor authorFromDAO = bookAuthorDAO.findByFirstNameAndLastName(author.getFirstName(), author.getLastName());
            if ( authorFromDAO == null){
                bookAuthorDAO.save(author);
                authorFromDAO = bookAuthorDAO.findByFirstNameAndLastName(author.getFirstName(), author.getLastName());
                authorsFromDAO.add(authorFromDAO);
            }else{
                authorsFromDAO.add(authorFromDAO);
            }
        }

        return authorsFromDAO;
    }

    private void addBookToUser(Book newBook, String username){
        UserProfile user = userProfileDAO.findByUsername(username);

        user.addBookToBookOffersList(newBook);
        userProfileDAO.save(user);
    }
      
    @Transactional
    public void deleteBookOffer(String username, int bookId){
        UserProfileServiceValidator userValidator = new UserProfileServiceValidator(userProfileDAO);
        UserProfile user =  userValidator.validateUsername(username);
        Book bookToDelete = bookDAO.findById(bookId);

        if (bookToDelete == null){
            throw new IllegalArgumentException("Book to delete doesn't exist");
        }else{
            if (user.deleteOfferFromUser(bookToDelete) == true){
                userProfileDAO.save(user);
                bookRequestDAO.deleteByRequestedBook(bookToDelete);
                bookDAO.delete(bookToDelete);
            }else{
                throw new IllegalArgumentException("Book does not belong to user");

            }
        }
    }

    public List<Book> retrieveBookOffers(String username){
        UserProfileServiceValidator userValidator = new UserProfileServiceValidator(userProfileDAO);
        UserProfile user = userValidator.validateUsername(username);

        return user.getBookOffers();
    }

    public Book getBookById(int bookId){
        return bookDAO.findById(bookId);
    }

    public List<Book> findBooksNotOfferedByUser(String username) {
        UserProfile user = userProfileDAO.findByUsername(username);
        List<Book> userBooks = user.getBookOffers();

        List<Book> books = bookDAO.findAll();

        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (userBooks.contains(book)) {
                iterator.remove();
            }
        }

        return books;
    }

}
