package com.SE2024.SocialBookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.SE2024.SocialBookStore.dtos.RegisterBookForm;
import com.SE2024.SocialBookStore.model.Book;
import com.SE2024.SocialBookStore.model.UserProfile;
import com.SE2024.SocialBookStore.service.UserProfileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserProfileService service;
    
    // @GetMapping("/register")
    // public String registerUser(Model model) {
    //     UserProfile profile = new UserProfile();
    //     model.addAttribute("UserProfile", profile);
    //     return "register";
    // }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserProfile profile) {
         try {
            UserProfile userProfile = service.registerUserProfileData(profile);
            return ResponseEntity.ok("User profile registered successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addbook")
    public ResponseEntity<String> addBookOffer(@RequestBody RegisterBookForm registerBookForm) {
         try {

            Book registeredBook = service.addBookOffer(registerBookForm.getBook(), registerBookForm.getUsername());
            return ResponseEntity.ok("Added");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/retrieveOffers")
    public List<Book> addBookOffer(@RequestParam String username) {
        return service.retrieveBookOffers(username);
    }
}