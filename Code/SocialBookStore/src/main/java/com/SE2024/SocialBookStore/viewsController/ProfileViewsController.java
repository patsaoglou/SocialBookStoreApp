package com.SE2024.SocialBookStore.viewsController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/app/profile")
public class ProfileViewsController {
    
    @GetMapping("/details")
    public String getProfileDetailsPage(){
        return "app/profile/details";
    }

    @GetMapping("/book_offers")
    public String getBookOffers() {
        return "app/profile/book_offers";
    }

    @GetMapping("/book_requests")
    public String getBookRequests() {
        return "app/profile/book_requests";
    }

    @GetMapping("/make_offer")
    public String makeBookOffer() {
        return "app/profile/make_offer";
    }
}
