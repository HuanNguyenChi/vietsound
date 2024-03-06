package com.huannguyen.vietsound.contoller;

import com.huannguyen.vietsound.dto.JwtAuthenticationResponse;
import com.huannguyen.vietsound.dto.SigninRequest;
import com.huannguyen.vietsound.entity.Category;
import com.huannguyen.vietsound.entity.CustomUserDetail;
import com.huannguyen.vietsound.entity.User;
import com.huannguyen.vietsound.service.CategoryService;
import com.huannguyen.vietsound.service.CustomUserDetailSevice;
import com.huannguyen.vietsound.service.JWTSerive;
import com.huannguyen.vietsound.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller

public class LoginController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTSerive jwtSerive;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomUserDetailSevice customUserDetailSevice;
    @GetMapping("/login")
    public String login(Model model){
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        return "login";
    }
    @PostMapping(path = "/login",produces = "text/plain; charset=UTF-8")
    public ResponseEntity<?> apiLogin(HttpServletRequest request, @RequestParam SigninRequest signinRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signinRequest.getUsername(),
                        signinRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtSerive.generateToken((CustomUserDetail) authentication.getDetails());
//        String jwtRefresh = jwtSerive.generateRefershToken((CustomUserDetail) authentication.getDetails());
        System.out.println(jwt);
        User user = (User) authentication.getPrincipal();
        List<String> roles = user.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt,null,user.getUsername(),user.getPassword(),roles));
    }
}
