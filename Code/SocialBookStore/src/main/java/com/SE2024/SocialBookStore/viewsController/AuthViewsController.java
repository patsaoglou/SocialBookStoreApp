package com.SE2024.SocialBookStore.viewsController;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SE2024.SocialBookStore.model.User;
import com.SE2024.SocialBookStore.model.UserProfile;
import com.SE2024.SocialBookStore.service.UserProfileService;
import com.SE2024.SocialBookStore.service.UserService;

import org.slf4j.Logger;


@Controller
public class AuthViewsController {

    private static final Logger logger = LoggerFactory.getLogger(AuthViewsController.class);

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @RequestMapping("/login")
    public String getLogin() {
        return "/auth/login";
    }
    
    @GetMapping("/register_user")
    public String getRegisterUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("userProfile", new UserProfile());

        return "/auth/register_user";
    }

    @PostMapping("/register_user_submit")
    public String registerUser(@ModelAttribute("user") User user, @ModelAttribute("userProfile") UserProfile userProfile, Model model) {
        
        if(userService.isUserPresent(user)){

            model.addAttribute("successMessage", "User already registered!");
            return "/auth/register_user";
        }
        
        try {
            userProfile.setUsername(user.getUsername());

            userProfileService.registerUserProfileData(userProfile);
            userService.saveUser(user);
            model.addAttribute("successMessage", "User registered successfully!");
        } catch (IllegalArgumentException e) {

            model.addAttribute("successMessage", e.getMessage());

            return "/auth/register_user";
        }

        return "/auth/login";
    }
    

    @GetMapping("/register_admin")
    public String getRegisterAdmin(Model model) {
        model.addAttribute("user", new User());
        return "/auth/register_Admin";
    }

    @PostMapping("/register_admin_submit")
    public String RegisterAdmin(@ModelAttribute("user") User user, Model model) {

        if(userService.isUserPresent(user)){
            model.addAttribute("successMessage", "Admin already registered!");
            return "/register_admin";
        }
        
        
        userService.saveAdmin(user);
        model.addAttribute("successMessage", "Admin registered successfully!");

        return "/auth/login";
    }
}
