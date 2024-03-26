package com.SE2024.SocialBookStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import com.SE2024.SocialBookStore.model.UserProfile;
import com.SE2024.SocialBookStore.service.UserProfileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserProfileService service;
    
    @GetMapping("/register")
    public String registerUser(Model model) {
        UserProfile profile = new UserProfile();
        model.addAttribute("UserProfile", profile);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(UserProfile profile) {
        service.registerUserProfile(profile);
        return "index";
    }
}
