package com.SE2024.SocialBookStore.viewsController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")
public class AuthViewsController {

    @GetMapping("login")
    public String getLogin() {
        return "/auth/login";
    }
    
    @GetMapping("register_user")
    public String getRegisterUser() {
        return "/auth/register_user";
    }

    @GetMapping("register_admin")
    public String getRegisterAdmin() {
        return "/auth/register_Admin";
    }
}
