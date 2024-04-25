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
import com.SE2024.SocialBookStore.service.BookService;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;


import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/app")
public class SocialBookViewsController {
    
    @Autowired
    BookService bookService;

    private static final Logger logger = LoggerFactory.getLogger(SocialBookViewsController.class);


    private String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        return currentPrincipalName;
    }


    @GetMapping("/home")
    public String getHome(Model model) {
        String user = getUsername();
        logger.info("--------------------------"+user);
        List<Book> books = bookService.findBooksNotOfferedByUser(user);

        model.addAttribute("userBookOffers", books);

        return "app/home";
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



    
    @GetMapping("/search")
    public String getSearch() {
        return "app/search";
    }
    
    @GetMapping("/book_details")
    public String getBookDetails() {
        return "app/book_details";
    }
    
}
