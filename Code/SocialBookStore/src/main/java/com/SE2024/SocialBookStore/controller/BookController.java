package com.SE2024.SocialBookStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import com.SE2024.SocialBookStore.model.Book;
import com.SE2024.SocialBookStore.model.UserProfile;
import com.SE2024.SocialBookStore.service.BookService;
import com.SE2024.SocialBookStore.service.UserProfileServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService service;
    
    @GetMapping("/addbookoffer")
    public String registerUser(Model model) {
        Book bookOffer = new Book();
        model.addAttribute("book", bookOffer);
        return "book";
    }

    @PostMapping("/addbookoffer")
    public String registerUser(Book bookOffer) {
        service.addBookOffer(bookOffer);
        return "index";
    }
}
