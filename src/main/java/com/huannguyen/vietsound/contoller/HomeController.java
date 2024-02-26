package com.huannguyen.vietsound.contoller;

import com.huannguyen.vietsound.entity.Album;
import com.huannguyen.vietsound.entity.Category;
import com.huannguyen.vietsound.entity.Singer;
import com.huannguyen.vietsound.entity.Song;
import com.huannguyen.vietsound.service.AlbumService;
import com.huannguyen.vietsound.service.CategoryService;
import com.huannguyen.vietsound.service.SingerService;
import com.huannguyen.vietsound.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Comparator;
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

    @GetMapping("")
    public String home(Model model){
        List<Singer> singerList = singerService.findAll();
        List<Album> albumList = albumService.findAll();
        List<Song> songList = songService.findAll();
        List<Category> categoryList = categoryService.findAll();

        Collections.sort(songList,new Comparator<Song>(){
            @Override
            public int compare(Song o1, Song o2) {
                return o1.getListens() > o2.getListens() ? 1 : -1;
            }
        });
        model.addAttribute("singerList",singerList);
        model.addAttribute("albumList",albumList);
        model.addAttribute("songList",songList);
        model.addAttribute("categoryList",categoryList);
        return "index";
    }
    @GetMapping("/album")
    public String getAllAlbum(Model model){
        List<Album> albumList = albumService.findAll();
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("albumList",albumList);
        model.addAttribute("categoryList",categoryList);
        return "albumhome";
    }
}
