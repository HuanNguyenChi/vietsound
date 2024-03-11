package com.huannguyen.vietsound.contoller;

import com.huannguyen.vietsound.entity.*;
import com.huannguyen.vietsound.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SingerService singerService;

    @Autowired
    private SongService songService;

    @GetMapping("/")
    public String home(Model model, HttpSession session){
        List<Singer> singerList = singerService.findAll();
        List<Singer> singerListLimit = singerService.findSingersLimit(0,4);
        List<Album> albumList = albumService.findAlbumsLimit(0,4);
        List<Song> songList = songService.findSongsLimit(0,12);
        List<Song> songListLimit = songService.findSongsLimit(0,4);
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("singerList",singerList);
        model.addAttribute("singerListLimit",singerListLimit);
        model.addAttribute("albumList",albumList);
        model.addAttribute("songList",songList);
        model.addAttribute("songListLimit",songListLimit);
        return "index";
    }
    @GetMapping("/album")
    public String getAllAlbum(Model model, Principal principal){
        List<Album> albumList = albumService.findAll();
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("albumList",albumList);
        return "albumhome";
    }
    @GetMapping("/song")
    public String getAllSong(Model model){
        List<Song> songList = songService.findSongsLimit(0,16);
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("songList",songList);
        return "songhome";
    }
    @GetMapping("/category")
    public String getAllCategory(Model model,Principal principal){
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        return "categoryhome";
    }
    @GetMapping("/tophit")
    public String tophit(Model model){
        List<Album> albumList = albumService.findAlbumsLimit(0,6);
        List<Category> categoryList = categoryService.findAll();
        List<Song> songList = songService.findSongsLimit(0,12);

        model.addAttribute("songList",songList);
        model.addAttribute("albumList",albumList);
        model.addAttribute("categoryList",categoryList);
        return "tophit";
    }
    @GetMapping("/contact")
    public String contact(Model model){

        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        return "contact";
    }

}
