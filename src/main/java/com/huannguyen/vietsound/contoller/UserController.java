package com.huannguyen.vietsound.contoller;

import com.huannguyen.vietsound.entity.*;
import com.huannguyen.vietsound.service.CategoryService;
import com.huannguyen.vietsound.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/{username}")
    public String getUser(Model model, @PathVariable("username") String username){
        User user = userService.findByUsername(username);
        model.addAttribute("user",user);
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        try {
            List<Song> songList = new ArrayList<>();
            List<Singer> singerList = new ArrayList<>();
            List<Album> albumList = new ArrayList<>();
            List<Category> categoryList1 = new ArrayList<>();
            if(user.getSongLike().size() > 0){
                songList = userService.findSongLimitFromUser(0,5,username);
            }
            if(user.getSingerList().size() > 0) {
                singerList = userService.findSingerLimitFromUser(0,6,username);
            }
            if(user.getAlbumList().size() > 0){
                albumList = userService.findAlbumLimitFromUser(0,6,username);
            }
            if(user.getCategoryLikeList().size() > 0){
                categoryList1 = userService.findCategoryLimitFromUser(0,6,username);
            }


            model.addAttribute("songList",songList);
            model.addAttribute("categoryList1",categoryList1);
            model.addAttribute("singerList",singerList);
            model.addAttribute("albumList",albumList);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "user/userhome";
    }

    @GetMapping("/updateinfo/{username}")
    public String userUpdateInfo(@PathVariable("username") String username, Model model){
        User user = userService.findByUsername(username);
        model.addAttribute("user",user);
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        return "user/userupdateinfo";
    }
}

