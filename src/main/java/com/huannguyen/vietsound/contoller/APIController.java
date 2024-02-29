package com.huannguyen.vietsound.contoller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class APIController {
    @PostMapping("/login")
    public String  apiLogin(HttpServletRequest request){
        return null;
    }
}
