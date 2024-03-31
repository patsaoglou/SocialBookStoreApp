package com.SE2024.SocialBookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.SE2024.SocialBookStore.dtos.BookRequestForm;
import com.SE2024.SocialBookStore.dtos.DeleteBookForm;
import com.SE2024.SocialBookStore.dtos.RegisterBookForm;
import com.SE2024.SocialBookStore.model.Book;
import com.SE2024.SocialBookStore.model.BookRequest;
import com.SE2024.SocialBookStore.model.UserProfile;
import com.SE2024.SocialBookStore.service.BookRequestService;
import com.SE2024.SocialBookStore.service.BookService;
import com.SE2024.SocialBookStore.service.UserProfileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserProfileService userService;
    
    @Autowired
    BookService bookService;
    
    @Autowired
    BookRequestService requestService;
    
    // @GetMapping("/register")
    // public String registerUser(Model model) {
    //     UserProfile profile = new UserProfile();
    //     model.addAttribute("UserProfile", profile);
    //     return "register";
    // }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserProfile profile) {
         try {
            UserProfile userProfile = userService.registerUserProfileData(profile);
            return ResponseEntity.ok("User profile registered successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addbook")
    public ResponseEntity<String> addBookOffer(@RequestBody RegisterBookForm registerBookForm) {
         try {

            Book registeredBook = bookService.addBookOffer(registerBookForm.getBook(), registerBookForm.getUsername());
            return ResponseEntity.ok("Added");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/requestabook")
    public ResponseEntity<String> addBookRequest(@RequestBody BookRequestForm bookRequest) {
         try {

            requestService.requestBook(bookRequest.getBookId(), bookRequest.getUsername());
            return ResponseEntity.ok("Added book request");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    


    @DeleteMapping("/deletebookrequest")
    public ResponseEntity<String> removeBookRequest(@RequestBody BookRequestForm bookRequest) {
         try {

            requestService.deleteBookRequest(bookRequest.getBookId(), bookRequest.getUsername());
            return ResponseEntity.ok("Book request deleted");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/deleteBookOffer")
    public ResponseEntity<String> addBookOffer(@RequestBody DeleteBookForm deleteBookForm) {
         try {

            bookService.deleteBookOffer(deleteBookForm.getUsername(), deleteBookForm.getBookId());
            return ResponseEntity.ok("Removed");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/retrieveOffers")
    public List<Book> addBookOffer(@RequestParam String username) {
        return bookService.retrieveBookOffers(username);
    }
    
    @GetMapping("/retrievebookrequesters")
    public List<BookRequest> retrieveBookRequesters(@RequestParam int bookId) {
        return requestService.getBookRequestsByBookId(bookId);
    }

    @GetMapping("/retrieveuserrequests")
    public List<BookRequest> retrieveUserRequests(@RequestParam int userId) {
        return requestService.getBookRequestsByUserId(userId);
    }


    @GetMapping("/profile")
    public UserProfile retrieveUser(@RequestParam String username) {
        return userService.retrieveUserProfile(username);
    }

    
}