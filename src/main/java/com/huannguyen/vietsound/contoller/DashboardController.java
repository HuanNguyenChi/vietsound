package com.huannguyen.vietsound.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class DashboardController {
    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard";
    }
}
