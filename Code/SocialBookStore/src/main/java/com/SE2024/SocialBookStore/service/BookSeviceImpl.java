package com.SE2024.SocialBookStore.service;

import java.util.ArrayList;
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
import com.SE2024.SocialBookStore.model.BookRequest;
import com.SE2024.SocialBookStore.model.UserProfile;

@Service
public class BookSeviceImpl implements BookService {

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

    SearchStrategy searchStrategy;

    public Book addBookOffer(Book bookData, String authors, String username) {

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

    private BookCategory addBookCategory(BookCategory bookCategory) {
        BookCategory bookCategoryFromDAO = bookCategoryDAO.findByCategoryName(bookCategory.getCategoryName());
        if (bookCategoryFromDAO == null) {
            bookCategoryDAO.save(bookCategory);
            bookCategoryFromDAO = bookCategoryDAO.findByCategoryName(bookCategory.getCategoryName());
        }
        return bookCategoryFromDAO;

    }

    private Set<BookAuthor> addAuthors(Set<BookAuthor> authors) {
        Set<BookAuthor> authorsFromDAO = new HashSet<>();
        for (BookAuthor author : authors) {
            BookAuthor authorFromDAO = bookAuthorDAO.findByFirstNameAndLastName(author.getFirstName(),
                    author.getLastName());
            if (authorFromDAO == null) {
                bookAuthorDAO.save(author);
                authorFromDAO = bookAuthorDAO.findByFirstNameAndLastName(author.getFirstName(), author.getLastName());
                authorsFromDAO.add(authorFromDAO);
            } else {
                authorsFromDAO.add(authorFromDAO);
            }
        }

        return authorsFromDAO;
    }

    private void addBookToUser(Book newBook, String username) {
        UserProfile user = userProfileDAO.findByUsername(username);

        user.addBookToBookOffersList(newBook);
        userProfileDAO.save(user);
    }

    @Transactional
    public void deleteBookOffer(String username, int bookId) {
        UserProfileServiceValidator userValidator = new UserProfileServiceValidator(userProfileDAO);
        UserProfile user = userValidator.validateUsername(username);
        Book bookToDelete = bookDAO.findById(bookId);

        if (bookToDelete == null) {
            throw new IllegalArgumentException("Book to delete doesn't exist");
        } else {
            if (user.deleteOfferFromUser(bookToDelete) == true) {
                userProfileDAO.save(user);
                bookRequestDAO.deleteByRequestedBook(bookToDelete);
                bookDAO.delete(bookToDelete);
            } else {
                throw new IllegalArgumentException("Book does not belong to user");

            }
        }
    }

    public List<Book> retrieveBookOffers(String username) {
        UserProfileServiceValidator userValidator = new UserProfileServiceValidator(userProfileDAO);
        UserProfile user = userValidator.validateUsername(username);

        return user.getBookOffers();
    }

    public Book getBookById(int bookId) {
        return bookDAO.findById(bookId);
    }

    public List<Book> showAvailableBooksToUser(String username) {
        UserProfile user = userProfileDAO.findByUsername(username);

        List<Book> socialBooks = findBooksNotOfferedByUser(user.getBookOffers(), bookDAO.findAll());
        socialBooks = findByRequestedBookAndNotStatusOrRequester(socialBooks, user.getId());      

        return socialBooks;
    }

    public List<Book> findByRequestedBookAndNotStatusOrRequester(List<Book> userBooks, int userId) {
        Iterator<Book> iterator = userBooks.iterator();

        while (iterator.hasNext()) {
            Book book = iterator.next();

            if (!bookRequestDAO.findByRequestedBookAndNotStatusOrRequester("ACCEPTED", book.getId(), userId)
                    .isEmpty()) {
                iterator.remove();
            }

        }
        return userBooks;
    }

    public List<Book> findBooksNotOfferedByUser(List<Book> userBooks, List<Book> books) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (userBooks.contains(book)) {
                iterator.remove();
            }
        }

        return books;
    }
    
    public List<Book> searchBooks(String keyword, String authors ,int strategy, String username){
        List<Book> searchBooks;
        if (authors == null || authors.isEmpty()){
            return null;
        }

        UserProfile user = userProfileDAO.findByUsername(username);
        List<String> authorList = Arrays.asList(authors.split(","));

        if (strategy == 1){
            TemplateSearchStrategy exactSearch = new ExactSearchStrategy();
            searchBooks = exactSearch.search(keyword, getAuthorsFromDAO(authorList), bookDAO);
            searchBooks = findBooksNotOfferedByUser(user.getBookOffers(), searchBooks);
            searchBooks = findByRequestedBookAndNotStatusOrRequester(searchBooks, user.getId());
            return searchBooks;
        }else if (strategy == 0){
            TemplateSearchStrategy approximateSearch = new ApproximateSearchStrategy();
            searchBooks = approximateSearch.search(keyword, getAuthorsFromDAO(authorList), bookDAO);
            searchBooks = findBooksNotOfferedByUser(user.getBookOffers(), searchBooks);
            searchBooks = findByRequestedBookAndNotStatusOrRequester(searchBooks, user.getId());
            return searchBooks;
        }

        
        return null;
    }

    private List<BookAuthor> getAuthorsFromDAO(List<String> authorList){
        List<BookAuthor> authorsFromDAO = new ArrayList<>();

        for (String authorName: authorList){
            List<String> authorFirstLast = Arrays.asList(authorName.split(" "));

            BookAuthor author = bookAuthorDAO.findByFirstNameAndLastName(authorFirstLast.get(0), authorFirstLast.get(1));
            if (author == null){
                return null;
            }
            authorsFromDAO.add(author);
        }

        return authorsFromDAO;
    }

    public List<Book> recommendBooksByFavouriteCategories(String username){
        List<Book> recommendedBooks = new ArrayList<>();
        Set<BookCategory> favouriteCategories = userProfileDAO.findByUsername(username).getFavouriteBookCategories();

        for(BookCategory category:favouriteCategories){
            recommendedBooks.addAll(bookDAO.findByBookCategory(category));
        }

        findBooksNotOfferedByUser(userProfileDAO.findByUsername(username).getBookOffers(), recommendedBooks);
        findByRequestedBookAndNotStatusOrRequester(recommendedBooks, userProfileDAO.findByUsername(username).getId());
        
        return recommendedBooks;   

    }

    public List<Book> recommendBooksByFavouriteBookAuthors(String username){
        List<Book> recommendedBooks = new ArrayList<>();
        Set<BookAuthor> favouriteAuthors = userProfileDAO.findByUsername(username).getFavouriteBookAuthors();

        for(BookAuthor author: favouriteAuthors){
            recommendedBooks.addAll(bookDAO.findByAuthors(author));
        }
        findBooksNotOfferedByUser(userProfileDAO.findByUsername(username).getBookOffers(), recommendedBooks);
        findByRequestedBookAndNotStatusOrRequester(recommendedBooks,userProfileDAO.findByUsername(username).getId());
        
        return recommendedBooks;   
    }

}
