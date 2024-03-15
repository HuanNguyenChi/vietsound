package com.huannguyen.vietsound.contoller;

import com.huannguyen.vietsound.entity.Category;
import com.huannguyen.vietsound.entity.Role;
import com.huannguyen.vietsound.entity.User;
import com.huannguyen.vietsound.service.CategoryService;
import com.huannguyen.vietsound.service.CustomUserDetailSevice;
import com.huannguyen.vietsound.service.RoleService;
import com.huannguyen.vietsound.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"user"})
public class LoginController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CustomUserDetailSevice customUserDetailSevice;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/login")
    public String login(Model model){
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        return "login/login";
    }

    @GetMapping("/register")
    public String registerHome(Model model){

        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        return "login/registerhome";
    }
    @PostMapping("/register")
    public String registerPost(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String email){
        User user = new User();
        user.setAddress("");
        user.setEmail(email);
        user.setPhone("");
        user.setFullname("");
        user.setUsername(username);
        user.setPassword(password);
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.findByName("ROLE_USER"));
        user.setRoles(roles);
        user.setAlbumList(new ArrayList<>());
        user.setSongLike(new ArrayList<>());
        user.setCategoryLikeList(new ArrayList<>());
        user.setSingerList(new ArrayList<>());
        User userCheck = userService.saveUser(user);

        return "redirect:/login";
    }
}
