package com.huannguyen.vietsound.contoller;

import com.huannguyen.vietsound.entity.Role;
import com.huannguyen.vietsound.entity.Singer;
import com.huannguyen.vietsound.entity.Song;
import com.huannguyen.vietsound.entity.User;
import com.huannguyen.vietsound.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private CustomUserDetailSevice customUserDetailSevice;
    @Autowired
    private StorageService storageService;
    @Autowired
    private RoleService roleService;
    @GetMapping("/delete/category")
    public String deleteCategory(@RequestParam("idCategory") int id){
        categoryService.delete(id);
        return "true";
    }
    @GetMapping("/delete/album")
    public String deleteAlbum(@RequestParam("idAlbum") int id){
        albumService.delete(id);
        return "true";
    }
    @GetMapping("/delete/singer")
    public String deleteSinger(@RequestParam("idSinger") int id){
        singerService.delete(id);
        return "true";
    }

    @GetMapping("/deleteuser")
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

    @GetMapping("/delete/song")
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
            res += "<h5 text = \"" + song.getName() + "\"></h5>";
            res += "<p text = \"" + song.getSingerOfSong().getStageName() + "\"></p>";
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
        System.out.println(res);
        return res;
    }

    @GetMapping("/likesong")
    public String likeSong(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return name;
    }

}
