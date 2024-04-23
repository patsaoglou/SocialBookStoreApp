package com.SE2024.SocialBookStore.viewsController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/")
public class AdminDashboardViewsController {
    
    @GetMapping("/dashboard")
    public String getAdminDashboardPage(){
        return "/admin/dashboard";
    }
}
