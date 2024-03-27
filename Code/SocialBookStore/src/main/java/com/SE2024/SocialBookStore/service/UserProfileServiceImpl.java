package com.SE2024.SocialBookStore.service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SE2024.SocialBookStore.dao.BookAuthorDAO;
import com.SE2024.SocialBookStore.dao.BookCategoryDAO;
import com.SE2024.SocialBookStore.dao.BookDAO;
import com.SE2024.SocialBookStore.dao.UserProfileDAO;
import com.SE2024.SocialBookStore.model.Book;
import com.SE2024.SocialBookStore.model.BookAuthor;
import com.SE2024.SocialBookStore.model.BookCategory;
import com.SE2024.SocialBookStore.model.UserProfile;

@Service
public class UserProfileServiceImpl implements UserProfileService{
    
    @Autowired
    UserProfileDAO userProfileDAO;

    @Autowired
    BookAuthorDAO bookAuthorDAO;
    
    @Autowired
    BookCategoryDAO bookCategoryDAO;
    
    @Autowired
    BookDAO bookDAO;



    public List<Book> retrieveBookOffers(String username){
        validateAddBookOfferUsername(username);

        UserProfile user = userProfileDAO.findByUsername(username);

        return user.getBookOffers();
    }

    private void validateAddBookOfferUsername(String username){
        validateIfUsernameNull(username);
        validateUsernameNonExistance(username);
    }

    public Book addBookOffer(Book bookData, String username){
        validateAddBookOfferUsername(username);
        validateBookObject(bookData);

        bookData.setAuthors(addAuthors(bookData.getAuthors()));
        bookData.setBookCategory(addBookCategory(bookData.getBookCategory()));
        
        Book newBook = bookDAO.save(bookData);
        addBookToUser(newBook, username);

        return newBook;
    }

    private void addBookToUser(Book newBook, String username){
        UserProfile user = userProfileDAO.findByUsername(username);

        user.addBookToBookOffersList(newBook);
        userProfileDAO.save(user);
    }

    private void validateBookObject(Book bookData){
        validateBookTitle(bookData.getBookTitle());
        validateBookSummary(bookData.getBookSummary());
        
        validateBookAuthors(bookData.getAuthors());
        
        validateBookCategory(bookData.getBookCategory());

    }

    private Set<BookAuthor> addAuthors(Set<BookAuthor> authors){
        Set<BookAuthor> authorsFromDAO = new HashSet<>();
        for (BookAuthor author: authors){
            BookAuthor authorFromDAO = bookAuthorDAO.findByFirstNameAndLastName(author.getFirstName(), author.getLastName());
            if ( authorFromDAO == null){
                bookAuthorDAO.save(author);
                authorFromDAO = bookAuthorDAO.findByFirstNameAndLastName(author.getFirstName(), author.getLastName());
                authorsFromDAO.add(authorFromDAO);
            }     
        }

        return authorsFromDAO;
    }
    
    private BookCategory addBookCategory(BookCategory bookCategory){
        BookCategory bookCategoryFromDAO = bookCategoryDAO.findByCategoryName(bookCategory.getCategoryName());
        if ( bookCategoryFromDAO == null){
            bookCategoryDAO.save(bookCategory);
            bookCategoryFromDAO = bookCategoryDAO.findByCategoryName(bookCategory.getCategoryName());
        }
        return bookCategoryFromDAO;
    
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

   

    public UserProfile registerUserProfileData(UserProfile profileData){
        validateUserProfileObject(profileData);
        userProfileDAO.save(profileData);

        return userProfileDAO.findByUsername(profileData.getUsername());        
    }

    private void validateUserProfileObject(UserProfile profileData){
        validateIfUsernameNull(profileData.getUsername());
        validateUsernameExistance(profileData.getUsername());
        validateFirstName(profileData.getFirstName());
        validateLastName(profileData.getLastName());
        validateTelephone(profileData.getTelephone());
        validateAddress(profileData.getAddress());
        validateAge(profileData.getAge());
    }

    private void validateUsernameExistance(String username){
        if (userProfileDAO.findByUsername(username) != null){
            throw new IllegalArgumentException("Username already exists");
        }
    }
    private void validateUsernameNonExistance(String username){
        if (userProfileDAO.findByUsername(username) == null){
            throw new IllegalArgumentException("Username doesn't exists");
        }
    }
    
    private void validateIfUsernameNull(String username){
        if (username == null){
            throw new IllegalArgumentException("Username is null");
        }
    }
    
    private void validateFirstName(String firstName){
        if (firstName == null){
            throw new IllegalArgumentException("First name cannot be null");
        }
    }

    private void validateLastName(String lastName){
        if (lastName == null){
            throw new IllegalArgumentException("Last name cannot be null");
        }
    }
    
    private void validateTelephone(String telephone){
        if (telephone == null){
            throw new IllegalArgumentException("Telephone cannot be null");
        }
    }

    private void validateAddress(String address){
        if (address == null){
            throw new IllegalArgumentException("Address cannot be null");
        }
    }

    private void validateAge(int age){
        if (age <= 0){
            throw new IllegalArgumentException("Age must be >0");
        }
    }

}
