package com.huannguyen.vietsound.contoller;

import com.huannguyen.vietsound.entity.Song;
import com.huannguyen.vietsound.entity.User;
import com.huannguyen.vietsound.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class APIController {
    @Autowired
    private SongService songService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomUserDetailSevice customUserDetailSevice;
    @PostMapping("/user/updateinfo/")
    public String userUpdateInfo(@RequestParam("username") String username,
                                 @RequestParam("fullname") String fullname,
                                 @RequestParam("address") String address,
                                 @RequestParam("phone") String phone,
                                 @RequestParam("email") String email){
        User user = userService.findByUsername(username);
        user.setEmail(email);
        user.setPhone(phone);
        user.setFullname(fullname);
        user.setAddress(address);
        User x =  userService.update(user);
        if(x != null) return "true";
        return "false";
    }
    @GetMapping(path = "/loadmoresongs")
    public String loadMoreSong(Model model, @RequestParam int page){
        List<Song> songList = songService.findSongsLimit(page,8);
        String res = "";
        for(Song song : songList){
            res += "<div class=\"col-12 col-sm-6 col-md-4 col-lg-3\" >";
            res += "<a href=\"/song/" + song.getId() + "\">";
            res += "<div class=\"single-album-area wow fadeInUp\" data-wow-delay=\"300ms\">";
            res += "<div class=\"album-thumb\">";
            res += "<img src=\"/img/bg-img/" + song.getImage()+ "\" alt=\"img-category\">";
            res += "<div class=\"play-icon\">\n" +
                    "                                <a href=\"#\" class=\"video--play--btn\"><span class=\"icon-play-button\"></span></a>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                        <div class=\"album-info\">";
            res += "<h5 text = \""+ song.getName()+"\"></h5>";
            res += "<p text = \""+ song.getSingerInSong().get(0).getStageName()+"\"></p>";
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
//        System.out.println(res);
        return res;
    }

    @GetMapping("/likesong")
    public String likeSong(Model model){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return name;
    }

}
