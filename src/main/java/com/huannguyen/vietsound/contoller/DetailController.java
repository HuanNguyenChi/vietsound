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
        model.addAttribute("song",song);
        model.addAttribute("singer",song.getSingerInSong().get(0));
        model.addAttribute("songOfSinger",song.getSingerInSong().get(0).getSongOfSinger());
        model.addAttribute("albumList",song.getSingerInSong().get(0).getAlbumList());
        return "songdetail";
    }
    @GetMapping("/singer/{id}")
    public String getSingerById(Model model, @PathVariable("id") int id){
        Singer singer = singerService.findById(id);
        model.addAttribute("singer",singer);
        model.addAttribute("songOfSinger",singer.getSongOfSinger());
        model.addAttribute("albumList",singer.getAlbumList());
        return "singerdetail";
    }
    @GetMapping("/album/{id}")
    public String getAlbumById(Model model, @PathVariable("id") int id){
        Album album = albumService.findById(id);

        model.addAttribute("songList",album.getSongInAlbum());
        model.addAttribute("singer",album.getSingerOfAlbum());
        model.addAttribute("albumDiff",album.getSingerOfAlbum().getAlbumList());

        return "albumdetail";
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
        return "categorydetail";
    }
}
