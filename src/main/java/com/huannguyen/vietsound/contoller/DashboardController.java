package com.huannguyen.vietsound.contoller;

import com.huannguyen.vietsound.entity.*;
import com.huannguyen.vietsound.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class DashboardController {
    @Autowired
    private UserService userService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SingerService singerService;

    @Autowired
    private SongService songService;

    @Autowired
    private StorageService storageService;

    @GetMapping()
    public String index(){
        return "redirect:admin/";
    }

    @GetMapping("/authority")
    public String updateRole(Model model){
        List<User> userList = userService.findAll();
        model.addAttribute("userList",userList);
        return "admin/authority";
    }

    // Admin Page
    @GetMapping("/")
    public String admin(Model model) {
        List<Singer> singerList = singerService.findAll();
        List<Singer> singerListLimit = singerService.findSingersLimit(0,4);
        List<Album> albumListLimit = albumService.findAlbumsLimit(0,4);
        List<Song> songList = songService.findSongsLimit(0,12);
        List<Song> songListLimit = songService.findSongsLimit(0,4);
        List<Category> categoryListLimit = categoryService.findCategoriesLimit(0,4);
        model.addAttribute("categoryListLimit",categoryListLimit);
        model.addAttribute("singerList",singerList);
        model.addAttribute("singerListLimit",singerListLimit);
        model.addAttribute("albumListLimit",albumListLimit);
        model.addAttribute("songList",songList);
        model.addAttribute("songListLimit",songListLimit);
        return "admin/dashboard";
    }

    //song page
    @GetMapping("/addsong")
    public String addSong(Model model){
        List<Album> albumList = albumService.findAll();
        List<Category> categoryList = categoryService.findAll();
        List<Singer> singerList = singerService.findAll();
        model.addAttribute("albumList",albumList);
        model.addAttribute("singerList",singerList);
        model.addAttribute("categoryList",categoryList);
        return "admin/addsong";
    }
    //category page
    @GetMapping("/addcategory")
    public String addCategory(){
        return "admin/addcategory";
    }
    //singer page
    @GetMapping("/addsinger")
    public String addSinger(Model model){
        return "admin/addsinger";
    }
    //album page
    @GetMapping("/addalbum")
    public String addAlbum(Model model){
        List<Category> categoryList = categoryService.findAll();
        List<Singer> singerList = singerService.findAll();
        model.addAttribute("singerList",singerList);
        model.addAttribute("categoryList",categoryList);
        return "admin/addalbum";
    }

    // save new song
    @PostMapping("/upload/song")
    public String uploadSong(@RequestParam("file") MultipartFile file,
                              @RequestParam("name") String name,
                              @RequestParam("link") String link,
                              @RequestParam("dateRelease")String dateRelease,
                              @RequestParam("album") int albumId,
                              @RequestParam("singer") int singerId,
                              @RequestParam("category") int categoryId,
                              @RequestParam("content") String content){
        Song song = new Song();
        if(file != null && !file.isEmpty()){
            storageService.store(file);
            String image = file.getOriginalFilename();
            song.setImage(image);
        }
        song.setContent(content);
        song.setDateRelease(dateRelease);
        song.setName(name);
        song.setLink(link);
        if(albumId > 0){
            song.setAlbum(albumService.findById(albumId));
        }
        if(categoryId > 0){
            song.setCategoryOfSong(categoryService.findById(categoryId));
        }

        song.setSingerOfSong(singerService.findById(singerId));
        songService.save(song);
        return "redirect:/admin/addsong";
    }
    //save new category
    @PostMapping("/upload/category")
    public String uploadCategory(@RequestParam("file") MultipartFile file,
                             @RequestParam("name") String name,
                             @RequestParam("description")String description){
        Category category = new Category();
        if(file != null && !file.isEmpty()){
            storageService.store(file);
            String image = file.getOriginalFilename();
            category.setImage(image);
        }
        category.setDescription(description);
        category.setName(name);
        category.setPopularity("New category");
        categoryService.save(category);
        return "redirect:/admin/addcategory";
    }
    //save new singer
    @PostMapping("/upload/singer")
    public String uploadSinger(@RequestParam("file") MultipartFile file,
                               @RequestParam("name") String name,
                               @RequestParam("stagename")String stageName,
                               @RequestParam("debut") String debut,
                               @RequestParam("story")String story){
        Singer singer = new Singer();
        if(file != null && !file.isEmpty()){
            storageService.store(file);
            String image = file.getOriginalFilename();
            singer.setImage(image);
        }
        singer.setDebut(debut);
        singer.setName(name);
        singer.setStory(story);
        singer.setStageName(stageName);
        return "redirect:/admin/addsinger";
    }
    //save new album
    @PostMapping("/upload/album")
    public String uploadAlbum(@RequestParam("file") MultipartFile file,
                              @RequestParam("name") String name,
                              @RequestParam("datepublic")String datePublic,
                              @RequestParam("singer") int singerId,
                              @RequestParam("category") int categoryId){
        Album album = new Album();

        if(file != null && !file.isEmpty()){
            storageService.store(file);
            String image = file.getOriginalFilename();
            album.setImage(image);
        }
        album.setName(name);
        album.setDatePublic(datePublic);
        album.setCategoryOfAlbum(categoryService.findById(categoryId));
        album.setSingerOfAlbum(singerService.findById(singerId));
        albumService.save(album);
        return "redirect:/admin/addalbum";
    }
    //page update song
    @GetMapping("/updatesong")
    public String updateSong(Model model){
        List<Song> songListLimit = songService.findSongsLimit(0,10);
        List<Song> songListAll = songService.findAll();

        model.addAttribute("songListLimit",songListLimit);
        if(songListAll.size() % 10 == 0){
            model.addAttribute("sumPage",songListAll.size()/10);
        }else{
            model.addAttribute("sumPage",songListAll.size()/10 + 1);
        }
        return "admin/updatesong";
    }
    //page update category
    @GetMapping("/updatecategory")
    public String updateCategory(Model model){
        List<Category> categoryListLimit = categoryService.findCategoriesLimit(0,10);
        model.addAttribute("categoryListLimit",categoryListLimit);
        return "admin/updatecategory";
    }
    //page update singer
    @GetMapping("/updatesinger")
    public String updateSinger(Model model){
        List<Singer> singerListLimit = singerService.findSingersLimit(0,10);
        model.addAttribute("singerListLimit",singerListLimit);
        return "admin/updatesinger";
    }
    //page update album
    @GetMapping("/updatealbum")
    public String updateAlbum(Model model){
        List<Album> albumListLimit = albumService.findAlbumsLimit(0,10);
        model.addAttribute("albumListLimit",albumListLimit);
        return "admin/updatealbum";
    }
    //page update detail song
    @GetMapping("/updatesongdetail/{id}")
    public String updateSongDetail(Model model, @PathVariable("id") int id){
        Song song = songService.findById(id);
        model.addAttribute("song",song);
        List<Album> albumList = albumService.findAll();
        List<Category> categoryList = categoryService.findAll();
        List<Singer> singerList = singerService.findAll();
        model.addAttribute("albumList",albumList);
        model.addAttribute("singerList",singerList);
        model.addAttribute("categoryList",categoryList);
        return "admin/updatesongdetail";
    }
    //page update detail category
    @GetMapping("/updatecategorydetail/{id}")
    public String updateCategoryDetail(Model model, @PathVariable("id") int id){
        Category category = categoryService.findById(id);
        model.addAttribute("category",category);
        return "admin/updatecategorydetail";
    }
    //page update detail singer
    @GetMapping("/updatesingerdetail/{id}")
    public String updateSingerDetail(Model model, @PathVariable("id") int id){
        Singer singer = singerService.findById(id);
        model.addAttribute("singer",singer);
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        return "admin/updatesingerdetail";
    }
    //page update detail album
    @GetMapping("/updatealbumdetail/{id}")
    public String updateAlbumDetail(Model model, @PathVariable("id") int id){
        Album album = albumService.findById(id);
        model.addAttribute("album",album);
        List<Category> categoryList = categoryService.findAll();
        List<Singer> singerList = singerService.findAll();
        model.addAttribute("singerList",singerList);
        model.addAttribute("categoryList",categoryList);
        return "admin/updatealbumdetail";
    }
    // update detail song
    @PutMapping("/updatesongdetail/{id}")
    public String updateSongDetail(@PathVariable("id") int id,
                                   @RequestParam("file") MultipartFile file,
                                   @RequestParam("name") String name,
                              @RequestParam("link") String link,
                              @RequestParam("dateRelease")String dateRelease,
                              @RequestParam("album") int albumId,
                              @RequestParam("singer") int singerId,
                              @RequestParam("category") int categoryId,
                              @RequestParam("content") String content){
        Song song = songService.findById(id);
        if(file != null && !file.isEmpty()){
            storageService.store(file);
            String image = file.getOriginalFilename();
            song.setImage(image);
        }
        if(content != song.getContent()){
            song.setContent(content);
        }
        if(dateRelease != song.getDateRelease()){
            song.setDateRelease(dateRelease);
        }
        if(name != song.getName()){
            song.setName(name);
        }
        if(song.getLink() != link){
            song.setLink(link);
        }
        if(song.getAlbum().getId() != albumId){
            song.setAlbum(albumService.findById(albumId));
        }
        if(categoryId != song.getCategoryOfSong().getId()){
            song.setCategoryOfSong(categoryService.findById(categoryId));
        }
        if(singerId != song.getSingerOfSong().getId()){
            song.setSingerOfSong(singerService.findById(id));
        }
        songService.save(song);
        return "redirect:/admin/updatesong";
    }
    //update detail category

    @PutMapping("/updatecategorydetail/{id}")
    public String updateCategoryDetail(@PathVariable("id") int id,
                                       @RequestParam("file") MultipartFile file,
                                       @RequestParam("name") String name,
                                       @RequestParam("description")String description){
        Category category = categoryService.findById(id);
        if(file != null && !file.isEmpty()){
            storageService.store(file);
            String image = file.getOriginalFilename();
            category.setImage(image);
        }
        if(name != category.getName()) category.setName(name);
        if(description != category.getDescription()) category.setDescription(description);
        return "redirect:/admin/updatecategorydetail";
    }
    //update detail singer
    @PutMapping("/updatesingerdetail/{id}")
    public String updateSingerDetail(@PathVariable("id") int id,
                                     @RequestParam("file") MultipartFile file,
                                     @RequestParam("name") String name,
                                     @RequestParam("stagename")String stageName,
                                     @RequestParam("debut") String debut,
                                     @RequestParam("story")String story){
        Singer singer = singerService.findById(id);
        if(file != null && !file.isEmpty()){
            storageService.store(file);
            String image = file.getOriginalFilename();
            singer.setImage(image);
        }
        if(name != singer.getName()) singer.setName(name);
        if(stageName != singer.getStageName()) singer.setStageName(stageName);
        if(debut != singer.getDebut()) singer.setDebut(debut);
        if(story != singer.getStory()) singer.setStory(story);
        singerService.save(singer);
        return "redirect:/admin/updatesingerdetail";
    }
    // update detail album
    @PutMapping("/updatealbumdetail/{id}")
    public String updateAlbumDetail(@PathVariable("id") int id,
                                    @RequestParam("file") MultipartFile file,
                                    @RequestParam("name") String name,
                                    @RequestParam("datepublic")String datePublic,
                                    @RequestParam("singer") int singerId,
                                    @RequestParam("category") int categoryId){
        Album album = albumService.findById(id);
        if(file != null && !file.isEmpty()){
            storageService.store(file);
            String image = file.getOriginalFilename();
            album.setImage(image);
        }
        if(name != album.getName()) album.setName(name);
        if(datePublic != album.getDatePublic()) album.setDatePublic(datePublic);
        if(singerId != album.getSingerOfAlbum().getId()) album.setSingerOfAlbum(singerService.findById(singerId));
        if(categoryId != album.getCategoryOfAlbum().getId()) album.setCategoryOfAlbum(categoryService.findById(categoryId));
        albumService.save(album);
        return "redirect:/admin/updatealbumdetail";
    }
}
