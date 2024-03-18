package com.huannguyen.vietsound.contoller;

import com.huannguyen.vietsound.entity.*;
import com.huannguyen.vietsound.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class APIController {
    @Autowired
    private SongService songService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SingerService singerService;
    @Autowired
    private UserService userService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private RoleService roleService;
    @DeleteMapping("/delete/category")
    public String deleteCategory(@RequestParam("idCategory") int id){
        categoryService.delete(id);
        return "true";
    }
    @DeleteMapping("/delete/album")
    public String deleteAlbum(@RequestParam("idAlbum") int id){
        albumService.delete(id);
        return "true";
    }
    @DeleteMapping("/delete/singer")
    public String deleteSinger(@RequestParam("idSinger") int id){
        singerService.delete(id);
        return "true";
    }

    @DeleteMapping("/deleteuser")
    public String deleteUserByAdmin(@RequestParam("id") int id){
        User user = userService.findById(id);
        user.getAlbumList().clear();
        user.getSingerList().clear();
        user.getCategoryLikeList().clear();
        user.getRoles().clear();
        userService.update(user);
        userService.delete(id);
        return "true";
    }
    @GetMapping("/updateroleforuser")
    public String updateRoleForUser(@RequestParam("id") int id,
                                    @RequestParam("role") String role,
                                    @RequestParam("isChecked") String isChecked) {
        User user = userService.findById(id);
        if (isChecked.equals("true")) {
            if (user.getRoles().size() == 1 && !user.getRoles().get(0).getName().equals(role)) {
                user.getRoles().add(roleService.findByName(role));
            } else {
                return "false";
            }
        } else if (isChecked.equals("false")) {
            if (user.getRoles().size() == 2) {
                Role roleRemove = user.getRoles().stream().filter(roleFilter -> roleFilter.getName().equals(role))
                        .findFirst().orElse(null);

                if (roleRemove != null) {
                    user.getRoles().remove(roleRemove);
                    System.out.println("check");
                } else {
                    return "false";
                }
            } else {
                return "false";
            }
        } else {
            return "false";
        }
//        roleService.saveRole(roleCheck);
        userService.update(user);
        return "true";
    }

    @DeleteMapping("/delete/song")
    public String deleteSong(@RequestParam("idSong") int id) {
        songService.delete(id);
        return "true";
    }

    @PostMapping("/upload/song")
    public String uploadImage(@RequestParam("file") MultipartFile file,
                              @RequestParam("name") String name,
                              @RequestParam("link") String link,
                              @RequestParam("dateRelease") String dateRelease,
                              @RequestParam("album") int albumId,
                              @RequestParam("singer") int singerId,
                              @RequestParam("category") int categoryId,
                              @RequestParam("content") String content) {
        storageService.store(file);
        String image = file.getOriginalFilename();
        Song song = new Song();
        song.setImage(image);
        song.setContent(content);
        song.setDislikes(0);
        song.setLikes(0);
        song.setDateRelease(dateRelease);
        song.setListens(0);
        song.setUserLikedSong(new ArrayList<>());
        song.setName(name);
        song.setLink(link);
        song.setAlbum(albumService.findById(albumId));
        song.setCategoryOfSong(categoryService.findById(categoryId));
        song.setSingerOfSong(singerService.findById(singerId));
        Song song1 = songService.save(song);
        if (song1 == null) return "false";
        return "true";
    }

    @PostMapping("/user/updateinfo/")
    public String userUpdateInfo(@RequestParam("username") String username,
                                 @RequestParam("fullname") String fullname,
                                 @RequestParam("address") String address,
                                 @RequestParam("phone") String phone,
                                 @RequestParam("email") String email) {
        User user = userService.findByUsername(username);
        user.setEmail(email);
        user.setPhone(phone);
        user.setFullname(fullname);
        user.setAddress(address);
        User x = userService.update(user);
        if (x != null) return "true";
        return "false";
    }

    @GetMapping("/user/addsongtouser")
    public String addSongToUser(@RequestParam("idMusic") int idMusic, Principal principal){
        if(principal != null){
            User user = userService.findByUsername(principal.getName());
            if(!user.hasSong(idMusic)) {
                user.getSongLike().add(songService.findById(idMusic));
                userService.update(user);
            }else {
                return "You haved liked this song!";
            }
        }else {
            return "Please Login!";
        }
        return "Success";
    }
    @GetMapping("/user/addsingertouser")
    public String addSingerToUser(@RequestParam("idSinger") int idSinger, Principal principal){
        if(principal != null){
            User user = userService.findByUsername(principal.getName());
            if(!user.hasSinger(idSinger)) {
                user.getSingerList().add(singerService.findById(idSinger));
                userService.update(user);
            }else {
                return "You haved liked this singer!";
            }
        }else {
            return "Please Login!";
        }
        return "Success";
    }
    @GetMapping("/user/removesongtouser")
    public String removeSongToUser(@RequestParam("idSong") int idSong, Principal principal){
        User user = userService.findByUsername(principal.getName());
        userService.update(userService.removeSongInUser(user,idSong));
        return "success";
    }
    @GetMapping(path = "/loadmoresongs")
    public String loadMoreSong(Model model, @RequestParam int page) {
        List<Song> songList = songService.findSongsLimit(page, 8);
        String res = "";
        for (Song song : songList) {
            res += "<div class=\"col-12 col-sm-6 col-md-4 col-lg-3\" >";
            res += "<a href=\"/song/" + song.getId() + "\">";
            res += "<div class=\"single-album-area wow fadeInUp\" data-wow-delay=\"300ms\">";
            res += "<div class=\"album-thumb\">";
            res += "<img src=\"/img/bg-img/" + song.getImage() + "\" alt=\"img-category\">";
            res += "<div class=\"play-icon\">\n" +
                    "                                <a href=\"#\" class=\"video--play--btn\"><span class=\"icon-play-button\"></span></a>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                        <div class=\"album-info\">";
            res += "<h5 >" + song.getName() + "</h5>";
            res += "<p>" + song.getSingerOfSong().getStageName() + "</p>";
            res += "               </div>\n" +
                    "                    </div>\n" +
                    "                </a>\n" +
                    "                <!-- Cart Button -->\n" +
                    "                <div class=\"cart-btn\" style=\"float: right; margin-right: 25px\">\n" +
                    "                    <p><span class=\"icon-heart\" ></span>\n" +
                    "                        <span class=\"fa fa-thumbs-o-down\" style=\"margin-left: 15px;\" ></span>\n" +
                    "                        <span class=\"icon-user\" style=\"margin-left: 15px;\" ></span>\n" +
                    "                    </p>\n" +
                    "                </div>\n" +
                    "            </div>";
        }

        return res;
    }

    @GetMapping("/loadmorealbums")
    public String loadMoreAlbum(@RequestParam int page){
        System.out.println(page);
        List<Album> albumListLimit = albumService.findAlbumsLimit(page,6);
        String res = "";
        for(Album album : albumListLimit){
            res += "<div class=\"col-12 col-md-6 col-lg-4\" >";
            res += "<div class=\"single-event-area mb-30\">";
            res += "<div class=\"event-thumbnail\">";
            res += "<img src=\"/img/bg-img/"  + album.getImage() + "\" alt=\"\">";
            res += "</div>";
            res += "<div class=\"event-text\">";
            res += "<h4>" + album.getName() + "</h4>";
            res += "<div class=\"event-meta-data\">";
            res += "<a href=\"#\" class=\"event-place\">" + album.getSingerOfAlbum().getStageName() + "</a>";
            res += "<a href=\"#\" class=\"event-date\" >" + album.getDatePublic() + "</a>";
            res += "</div>";
            res += "<a href=\"/album/ "  + album.getId() + " \" class=\"btn see-more-btn\">See Album</a>";
            res += "</div>";
            res += "</div>";
            res += "</div> ";
        }

        return res;
    }

    @GetMapping("/loadmorecategorys")
    public String loadMoreCategory(@RequestParam int page){
        List<Category> categoryListLimit = categoryService.findCategoriesLimit(page,6);
        String res = "";
        for(Category category : categoryListLimit){
            res += "<div class=\"col-12 col-md-6 col-lg-4\">";
            res += "<div class=\"single-event-area mb-30\" >";
            res += "<div class=\"event-thumbnail\">";
            res += "<img src=\"/img/bg-img/" + category.getImage() + "\" alt=\"\">";
            res += "</div>";
            res += "<div class=\"event-text\" style=\"width: 100%\">";
            res += " <h4 >" + category.getName() + "</h4>";
            res += "<a href=\"/category/"  + category.getId()+ "\" class=\"btn see-more-btn\">See Category</a>";
            res += "</div>";
            res += "</div>";
            res += "</div>";
        }
        return res;
    }
    @GetMapping("/loadmoresingers")
    public String loadMoreSinger(@RequestParam int page){
        List<Singer> singerListLimit = singerService.findSingersLimit(page,6);
        String res = "";
        for(Singer singer : singerListLimit){
            res += "<div class=\"col-12 col-md-6 col-lg-4\"\">";
            res += "<div class=\"single-event-area mb-30\" >";
            res += "<div class=\"event-thumbnail\">";
            res += "<img src=\"/img/bg-img/"  + singer.getImage() + "\" alt=\"\">";
            res += "</div>";
            res += "<div class=\"event-text\" style=\"width: 100%\">";
            res += "<h4>" + singer.getStageName()+ "</h4>";
            res += "<a href=\"/singer/" + singer.getId() + "\" class=\"btn see-more-btn\">See Artist</a>";
            res += "</div>";
            res += "</div>";
            res += " </div>";
        }
        return res;
    }


}
