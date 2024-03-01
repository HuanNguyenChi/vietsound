package com.huannguyen.vietsound.contoller;

import com.huannguyen.vietsound.entity.Album;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/song")
public class SongController {
    @Autowired
    private SingerService singerService;
    @Autowired
    private SongService songService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AlbumService albumService;

    @GetMapping("/{id}")
    public String getSongById(Model model, @PathVariable("id") int id){
        Song song = songService.findById(id);
        Singer singer = singerService.findById(song.getSingerInSong().get(0).getId());
        List<Song> songOfSinger = singer.getSongOfSinger();
        List<Album> albumList = albumService.findBySingerOfAlbum(singer);
        model.addAttribute("song",song);
        model.addAttribute("singer",singer);
        model.addAttribute("songOfSinger",songOfSinger);
        model.addAttribute("albumList",albumList);
        return "songdetail";
    }
}
