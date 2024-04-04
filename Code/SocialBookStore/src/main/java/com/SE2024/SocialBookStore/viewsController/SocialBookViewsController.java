package com.SE2024.SocialBookStore.viewsController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/app")
public class SocialBookViewsController {
    
    @GetMapping("/home")
    public String getHome() {
        return "app/home";
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
