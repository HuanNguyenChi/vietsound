package com.huannguyen.vietsound.contoller;

import com.huannguyen.vietsound.entity.Album;
import com.huannguyen.vietsound.entity.Category;
import com.huannguyen.vietsound.entity.Singer;
import com.huannguyen.vietsound.entity.Song;
import com.huannguyen.vietsound.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class DetailController {
    @Autowired
    private SingerService singerService;
    @Autowired
    private SongService songService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AlbumService albumService;

    @GetMapping("/song/{id}")
    public String getSongById(Model model, @PathVariable("id") int id){
        Song song = songService.findById(id);
//        Singer singer = singerService.findById(song.getSingerInSong().get(0).getId());
//        List<Song> songOfSinger = singer.getSongOfSinger();
//        List<Album> albumList = albumService.findBySingerOfAlbum(singer);
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("song",song);
        model.addAttribute("singer",song.getSingerOfSong());
        model.addAttribute("songOfSinger",song.getSingerOfSong().getSongList());
        model.addAttribute("albumList",song.getSingerOfSong().getAlbumList());
        return "user/songdetail";
    }
    @GetMapping("/singer/{id}")
    public String getSingerById(Model model, @PathVariable("id") int id){
        Singer singer = singerService.findById(id);
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("singer",singer);
        model.addAttribute("songOfSinger",singer.getSongList());
        model.addAttribute("albumList",singer.getAlbumList());
        return "user/singerdetail";
    }
    @GetMapping("/album/{id}")
    public String getAlbumById(Model model, @PathVariable("id") int id){
        Album album = albumService.findById(id);
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("songList",album.getSongInAlbum());
        model.addAttribute("singer",album.getSingerOfAlbum());
        model.addAttribute("albumDiff",album.getSingerOfAlbum().getAlbumList());

        return "user/albumdetail";
    }
    @GetMapping("/category/{id}")
    public String getCategoryById(Model model, @PathVariable("id") int id){
        Category category = categoryService.findById(id);
//        Singer singer = singerService.findById(song.getSingerInSong().get(0).getId());
//        List<Song> songOfSinger = singer.getSongOfSinger();
//        List<Album> albumList = albumService.findBySingerOfAlbum(singer);
        model.addAttribute("category",category);
        model.addAttribute("songList",category.getSongInCategory());
//        model.addAttribute("singerOfCategory",category.get);
        model.addAttribute("albumInCategory",category.getAlbumInCategory());
        return "user/categorydetail";
    }
}
