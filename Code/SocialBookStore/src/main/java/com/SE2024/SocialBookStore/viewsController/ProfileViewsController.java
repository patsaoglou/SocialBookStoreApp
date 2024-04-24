package com.SE2024.SocialBookStore.viewsController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SE2024.SocialBookStore.model.BookAuthor;
import com.SE2024.SocialBookStore.model.Book;
import com.SE2024.SocialBookStore.model.UserProfile;
import com.SE2024.SocialBookStore.service.BookService;
import com.SE2024.SocialBookStore.service.UserProfileService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.LoggerFactory;

import java.util.Set;

import org.slf4j.Logger;

@Controller
@RequestMapping("/app/profile")
public class ProfileViewsController {

    @Autowired
    UserProfileService userService;
    
    @Autowired
    BookService bookService;
    
    private static final Logger logger = LoggerFactory.getLogger(ProfileViewsController.class);

    private String getAuthenticatedUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        return currentPrincipalName;
    }

    @GetMapping("/details")
    public String getProfileDetailsPage(Model model){
        UserProfile usernameDetails = userService.retrieveUserProfile(getAuthenticatedUsername());

        model.addAttribute("user", usernameDetails);
        return "app/profile/details";
    }


    @GetMapping("/make_offer")
    public String makeBookOffer(Model model) {
             
        model.addAttribute("book", new Book());

        return "app/profile/make_offer";
    }
    

    @PostMapping("/make_offer_submit")
    public String submitBookOffer(@ModelAttribute("book") Book newBook, @RequestParam("bookAuthors") String authors, Model model) {

        bookService.addBookOffer(newBook, authors, getAuthenticatedUsername());        

        return "redirect:/app/profile/book_offers";
    }

    @GetMapping("/book_offers")
    public String getBookOffers(Model model) {

        model.addAttribute("userBookOffers", bookService.retrieveBookOffers(getAuthenticatedUsername()));

        return "app/profile/book_offers";
    }


    @GetMapping("/get_book_details")
    public String getBookDetails(@RequestParam("bookId") int bookId, Model model) {
        Book book = bookService.getBookById(bookId);

        String authorsString = "";

        Set<BookAuthor> bookAuthors = book.getAuthors();
        for (BookAuthor author: bookAuthors){
            authorsString += author.getFirstName()+" "+ author.getLastName()+ ", ";
        }
        model.addAttribute("bookAuthors", authorsString);
        model.addAttribute("book", bookService.getBookById(bookId));
        return "app/book_details";
    }

    @PostMapping("/delete_book_offer")
    public String deleteBookOffer(@RequestParam("bookId") int bookId, Model model) {

        bookService.deleteBookOffer(getAuthenticatedUsername(), bookId);
        
        return "redirect:/app/profile/book_offers";
    }
    
    @GetMapping("/book_requests")
    public String getBookRequests() {
        return "app/profile/book_requests";
    }

}
