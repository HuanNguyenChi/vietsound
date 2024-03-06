package com.huannguyen.vietsound.contoller;

import com.huannguyen.vietsound.entity.Album;
import com.huannguyen.vietsound.entity.Category;
import com.huannguyen.vietsound.entity.Singer;
import com.huannguyen.vietsound.entity.Song;
import com.huannguyen.vietsound.service.AlbumService;
import com.huannguyen.vietsound.service.CategoryService;
import com.huannguyen.vietsound.service.SingerService;
import com.huannguyen.vietsound.service.SongService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String home(Model model, Principal principal,HttpServletRequest request){
        List<Singer> singerList = singerService.findAll();
        List<Singer> singerListLimit = singerService.findSingersLimit(0,4);
        List<Album> albumList = albumService.findAlbumsLimit(0,4);
        List<Song> songList = songService.findSongsLimit(0,12);
        List<Song> songListLimit = songService.findSongsLimit(0,4);
        List<Category> categoryList = categoryService.findAll();

//        try{
//            String check = request.getUserPrincipal().getName();
//            System.out.println(check);
////            String username = principal.getName();
////            System.out.println(username);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

//        String jwt = request.getParameter("token",);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("singerList",singerList);
        model.addAttribute("singerListLimit",singerListLimit);
        model.addAttribute("albumList",albumList);
        model.addAttribute("songList",songList);
        model.addAttribute("songListLimit",songListLimit);

        return "index";
    }
    @GetMapping("/album")
    public String getAllAlbum(Model model){
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
    public String getAllCategory(Model model){
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
