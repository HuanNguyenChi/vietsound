package com.huannguyen.vietsound.contoller;

import com.huannguyen.vietsound.entity.Song;
import com.huannguyen.vietsound.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class DashboardController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public String index(){
        return "redirect:admin/";
    }

    @GetMapping("/")
    public String admin(Model model) {
//        List<Song> songListLimit = userService.findSongLimitFromUser(0,10);

//        model.addAttribute("songListLimit",songListLimit);
        return "dashboard";
    }
    @GetMapping("/check")
    public String check() {
        return "check";
    }
}
