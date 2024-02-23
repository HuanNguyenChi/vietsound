package com.huannguyen.vietsound.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class DashboardController {

    @GetMapping()
    public String index(){
        return "redirect:admin/";
    }

    @GetMapping("/")
    public String admin() {
        return "dashboard";
    }
    @GetMapping("/check")
    public String check() {
        return "check";
    }
}
