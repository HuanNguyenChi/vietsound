package com.huannguyen.vietsound.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("")
    public String home(){
        System.out.println("whfhwiehfi");
        return "index";
    }
}
