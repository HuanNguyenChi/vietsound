package com.huannguyen.vietsound.contoller;

import com.huannguyen.vietsound.dto.JwtAuthenticationResponse;
import com.huannguyen.vietsound.dto.SigninRequest;
import com.huannguyen.vietsound.entity.CustomUserDetail;
import com.huannguyen.vietsound.entity.Song;
import com.huannguyen.vietsound.entity.User;
import com.huannguyen.vietsound.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class APIController {
    @Autowired
    private SongService songService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTSerive jwtSerive;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomUserDetailSevice customUserDetailSevice;

//    @PostMapping(path = "/login",produces = "text/plain; charset=UTF-8")
//    public ResponseEntity<?> apiLogin(HttpServletRequest request, @RequestParam SigninRequest signinRequest){
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        signinRequest.getUsername(),
//                        signinRequest.getPassword()
//                )
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtSerive.generateToken((CustomUserDetail) authentication.getDetails());
////        String jwtRefresh = jwtSerive.generateRefershToken((CustomUserDetail) authentication.getDetails());
//        System.out.println(jwt);
//        User user = (User) authentication.getPrincipal();
//        List<String> roles = user.getAuthorities().stream()
//                .map(item -> item.getAuthority())
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt,null,user.getUsername(),user.getPassword(),roles));
//    }
//@GetMapping(path = "/loadmoresongs")
//public String loadMoreSong(Model model, @RequestParam int page){
//    List<Song> songList = songService.findSongsLimit(page,4);
//    String res = "";
//    for(Song song : songList){
//        res += "<div class=\"col-12 col-sm-6 col-md-4 col-lg-3\" >";
//        res += "<a th:href=\"/song/" + song.getId() + "\">";
//        res += "<div class=\"single-album-area wow fadeInUp\" data-wow-delay=\"300ms\">";
//        res += "<div class=\"album-thumb\">";
//        res += "<img th:src=\"/img/bg-img/" + song.getImage()+ "\" alt=\"img-category\">";
//        res += "<div class=\"play-icon\">\n" +
//                "                                <a href=\"#\" class=\"video--play--btn\"><span class=\"icon-play-button\"></span></a>\n" +
//                "                            </div>\n" +
//                "                        </div>\n" +
//                "                        <div class=\"album-info\">";
//        res += "<h5 th:text = \""+ song.getName()+"\"></h5>";
//        res += "<p th:text = \""+ song.getSingerInSong().get(0).getStageName()+"\"></p>";
//        res += "               </div>\n" +
//                "                    </div>\n" +
//                "                </a>\n" +
//                "                <!-- Cart Button -->\n" +
//                "                <div class=\"cart-btn\" style=\"float: right; margin-right: 25px\">\n" +
//                "                    <p><span class=\"icon-heart\" ></span>\n" +
//                "                        <span class=\"fa fa-thumbs-o-down\" style=\"margin-left: 15px;\" ></span>\n" +
//                "                        <span class=\"icon-user\" style=\"margin-left: 15px;\" ></span>\n" +
//                "                    </p>\n" +
//                "                </div>\n" +
//                "            </div>";
//    }
////        System.out.println(res);
//    return res;
//}
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
}
