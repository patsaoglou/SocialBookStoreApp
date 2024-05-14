package com.SE2024.SocialBookStore.viewsController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SE2024.SocialBookStore.dtos.BookDTO;
import com.SE2024.SocialBookStore.service.BookRequestService;
import com.SE2024.SocialBookStore.service.BookService;
import com.SE2024.SocialBookStore.service.UserProfileService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/app/profile")
public class ProfileViewsController {

    @Autowired
    UserProfileService userService;
    
    @Autowired
    BookService bookService;

    @Autowired
    BookRequestService bookRequestService;
    
    private String getAuthenticatedUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        return currentPrincipalName;
    }

    @GetMapping("/details")
    public String getProfileDetailsPage(Model model){

        model.addAttribute("user", userService.retrieveUserProfile(getAuthenticatedUsername()));
        return "app/profile/details";
    }


    @GetMapping("/make_offer")
    public String makeBookOffer(Model model) {
             
        model.addAttribute("book", new BookDTO());

        return "app/profile/make_offer";
    }
    

    @PostMapping("/make_offer_submit")
    public String submitBookOffer(@ModelAttribute("book") BookDTO newBook, Model model) {

        bookService.addBookOffer(newBook, getAuthenticatedUsername());        

        return "redirect:/app/profile/my_book_offers";
    }

    @GetMapping("/my_book_offers")
    public String getBookOffers(Model model) {

        model.addAttribute("userBookOffers", bookService.retrieveBookOffers(getAuthenticatedUsername()));

        return "app/profile/book_offers";
    }


    @PostMapping("/delete_book_offer")
    public String deleteBookOffer(@RequestParam("bookId") int bookId, Model model) {

        bookService.deleteBookOffer(getAuthenticatedUsername(), bookId);
        
        return "redirect:/app/profile/my_book_offers";
    }
    
    @GetMapping("/book_offer_details")
    public String getBookOfferDetails(@RequestParam("bookId") int bookId, Model model) {

        model.addAttribute("book",  bookService.getBookById(bookId));
        model.addAttribute("bookRequesters", bookRequestService.retrieveRequestingUsers(bookId));
        

        return "app/profile/book_offer_details";
    }


    @GetMapping("/my_book_requests")
    public String getMyBookRequests(Model model) {

        model.addAttribute("myBookRequests", bookRequestService.retrieveBookRequests(getAuthenticatedUsername()));

        return "app/profile/my_book_requests";
    }    

    @PostMapping("/cancel_my_request")
    public String cancelMyBookRequests(@RequestParam("bookId") int bookId, Model model) {
        
        bookRequestService.deleteBookRequest(bookId, getAuthenticatedUsername());

        return "redirect:/app/profile/my_book_requests";
    }

    @PostMapping("/select_requester")
    public String selectRequester(@RequestParam("requestId") int requestId,  Model model) {
        
        bookRequestService.selectRequester(requestId);

        return "redirect:/app/profile/my_book_offers";
    }

    @PostMapping("/add_favourite_author")
    public String add_favourite_author(@RequestParam("authorName") String authorName,  Model model) {
        
        userService.addFavouriteAuthor(authorName, getAuthenticatedUsername());
        return "redirect:/app/profile/details";
    }

    @PostMapping("/add_favourite_category")
    public String add_favourite_category(@RequestParam("categoryName") String categoryName,  Model model) {
        
        userService.addFavouriteCategory(categoryName, getAuthenticatedUsername());
        return "redirect:/app/profile/details";
    }
    
}

