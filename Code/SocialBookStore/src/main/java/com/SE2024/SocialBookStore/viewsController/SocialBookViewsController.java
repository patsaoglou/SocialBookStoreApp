package com.SE2024.SocialBookStore.viewsController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SE2024.SocialBookStore.dtos.SearchFormDTO;
import com.SE2024.SocialBookStore.service.books.BookRequestService;
import com.SE2024.SocialBookStore.service.books.BookService;
import com.SE2024.SocialBookStore.service.userProfile.UserProfileService;

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

    private String getAuthenticatedUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        return currentPrincipalName;
    }


    @GetMapping("/home")
    public String getHome(Model model) {
        model.addAttribute("userBookOffers", bookService.showAvailableBooksToUser(getAuthenticatedUsername()));

        return "app/home";
    }

    
    @GetMapping("/get_book_details")
    public String getBookDetails(@RequestParam int bookId, Model model) {
        model.addAttribute("book", bookService.getBookById(bookId));
        
        return "app/book_details";
    }

    @PostMapping("/request_book")
    public String requestBook(@RequestParam int bookId, Model model) {
        bookRequestService.requestBook(bookId, getAuthenticatedUsername());
        return "redirect:/app/profile/my_book_requests";
    }

    @GetMapping("/user_details")
    public String getUserDetails(@RequestParam String username, Model model) {
        model.addAttribute("user", userProfileService.retrieveUserProfile(username));

        return "app/user_details";
    }
    
    @GetMapping("/search")
    public String getSearch(Model model) {

        SearchFormDTO searchFormDTO = new SearchFormDTO();
        model.addAttribute("searchForm", searchFormDTO);
        return "app/search";
    }

    @GetMapping("/search_submit")
    public String getSearchSubmit(@RequestParam(name = "searchKeyword", required = false) String keyword, 
                                    @RequestParam(name = "searchStrategy", required = false) int strategy, 
                                    @RequestParam(required = false) String authors, Model model) {
        
        SearchFormDTO searchFormDTO = new SearchFormDTO(keyword, strategy, authors);
        
        model.addAttribute("searchForm", searchFormDTO);
        model.addAttribute("userBookOffers", bookService.searchBooks(searchFormDTO, getAuthenticatedUsername()));
        
        return "app/search";
    }   

    @GetMapping("/recommend_books")
    public String getRecommendedBook(Model model) {
        
        model.addAttribute("categoryBooks", bookService.recommendBooksByFavouriteCategories(getAuthenticatedUsername()));
        model.addAttribute("authorBooks", bookService.recommendBooksByFavouriteBookAuthors(getAuthenticatedUsername()));
        
        return "app/recommendations";
    }    
}
