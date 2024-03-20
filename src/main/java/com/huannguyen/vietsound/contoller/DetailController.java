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

import java.util.ArrayList;
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
    @Autowired
    private UserService userService;

    @GetMapping("/song/{id}")
    public String getSongById(Model model, @PathVariable("id") int id){
        Song song = songService.findById(id);
        List<Category> categoryList = categoryService.findAll();
        List<Album> albumList = new ArrayList<>();
        List<Song> songList = new ArrayList<>();
        if(albumService.findAlbumsBySingerOfAlbumLimit(song.getSingerOfSong(),0,8).size() > 0){
            albumList = albumService.findAlbumsBySingerOfAlbumLimit(song.getSingerOfSong(),0,8);
        }
        if(songService.findSongsBySingerOfSongLimit(song.getSingerOfSong(),0,8).size() > 0){
            songList = songService.findSongsBySingerOfSongLimit(song.getSingerOfSong(),0,8);
        }

        model.addAttribute("categoryList",categoryList);
        model.addAttribute("song",song);
        model.addAttribute("singer",song.getSingerOfSong());
        song.setListens(song.getListens() + 1);
        model.addAttribute("albumList",albumList);
        model.addAttribute("songOfSinger",songList);
        songService.save(song);
        return "user/songdetail";
    }
    @GetMapping("/singer/{id}")
    public String getSingerById(Model model, @PathVariable("id") int id){
        Singer singer = singerService.findById(id);
        List<Category> categoryList = categoryService.findAll();
        List<Song> songList = new ArrayList<>();
        List<Album> albumList = new ArrayList<>();

        model.addAttribute("categoryList",categoryList);
        model.addAttribute("singer",singer);
        if(songService.findSongsBySingerOfSongLimit(singer,0,8).size() > 0) {
            songList = songService.findSongsBySingerOfSongLimit(singer,0,8);
        }
        if(albumService.findAlbumsBySingerOfAlbumLimit(singer,0,8).size() > 0){
            albumList = albumService.findAlbumsBySingerOfAlbumLimit(singer,0,8);
        }
        model.addAttribute("songOfSinger",songList);
        model.addAttribute("albumList",albumList);
        return "user/singerdetail";
    }
    @GetMapping("/album/{id}")
    public String getAlbumById(Model model, @PathVariable("id") int id){
        Album album = albumService.findById(id);
        List<Category> categoryList = categoryService.findAll();
        List<Song> songList = new ArrayList<>();
        List<Album> albumDiff = new ArrayList<>();
        if(album.getSongInAlbum().size() != 0){
            songList = album.getSongInAlbum();
        }
        if(album.getSingerOfAlbum().getAlbumList().size() != 0){
            albumDiff = album.getSingerOfAlbum().getAlbumList();
        }
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("songList",songList);
        model.addAttribute("singer",album.getSingerOfAlbum());
        model.addAttribute("albumDiff",albumDiff);

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
