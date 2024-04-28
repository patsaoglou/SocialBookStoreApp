package com.SE2024.SocialBookStore.viewsController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SE2024.SocialBookStore.model.Book;
import com.SE2024.SocialBookStore.model.BookAuthor;
import com.SE2024.SocialBookStore.model.UserProfile;
import com.SE2024.SocialBookStore.service.BookRequestService;
import com.SE2024.SocialBookStore.service.BookService;
import com.SE2024.SocialBookStore.service.UserProfileService;

import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/app")
public class SocialBookViewsController {
    
    @Autowired
    BookService bookService;

    @Autowired
    BookRequestService bookRequestService;

    @Autowired
    UserProfileService userProfileService;

    private static final Logger logger = LoggerFactory.getLogger(SocialBookViewsController.class);


    private String getAuthenticatedUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        return currentPrincipalName;
    }


    @GetMapping("/home")
    public String getHome(Model model) {
        List<Book> books = bookService.findBooksNotOfferedByUser(getAuthenticatedUsername());

        model.addAttribute("userBookOffers", books);

        return "app/home";
    }

    
    @GetMapping("/get_book_details")
    public String getBookDetails(@RequestParam("bookId") int bookId, Model model) {
        model.addAttribute("book", bookService.getBookById(bookId));
        
        return "app/book_details";
    }

    @PostMapping("/request_book")
    public String requestBook(@RequestParam("bookId") int bookId, Model model) {
        bookRequestService.requestBook(bookId, getAuthenticatedUsername());
        return "redirect:/app/profile/my_book_requests";
    }

    @GetMapping("/user_details")
    public String getUserDetails(@RequestParam("username") String username, Model model) {
        model.addAttribute("user", userProfileService.retrieveUserProfile(username));

        return "app/user_details";
    }

    
    @GetMapping("/search")
    public String getSearch() {
        return "app/search";
    }
    
    @GetMapping("/book_details")
    public String getBookDetails() {
        return "app/book_details";
    }
    
}
