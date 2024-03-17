package com.huannguyen.vietsound.service.impl;

import com.huannguyen.vietsound.entity.*;
import com.huannguyen.vietsound.repo.RoleRepo;
import com.huannguyen.vietsound.repo.UserRepo;
import com.huannguyen.vietsound.service.CategoryService;
import com.huannguyen.vietsound.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public User update(User user) {
        return userRepo.save(user);
    }

    @Override
    public void delete(int id) {
         userRepo.deleteById(id);
    }

    @Override
    public void addToUser(String username, String rolename) {
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(rolename);
        try {
            user.getRoles().add(role);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findById(int id) {
        return userRepo.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepo.findUserByUsername(username);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepo.existsUserByEmail(email);
    }

    @Override
    public boolean existsUserByUsername(String username) {
        return userRepo.existsUserByUsername(username);
    }

    @Override
    public boolean existsUserByUsernameAndPassword(String username, String password) {
        return userRepo.existsUserByUsernameAndPassword(username,password);
    }

    @Override
    public List<Song> findSongLimitFromUser(int page, int size, String username) {
        User user = userRepo.findByUsername(username);
        List<Song> songList = new ArrayList<>();
        int length = (page+1) * size;
        if(user.getSongLike().size() <= (page+1) * size)   length = user.getSongLike().size();
        for(int index=page*size;index<length;index++){
            songList.add(user.getSongLike().get(index));
        }
        return songList;
    }

    @Override
    public List<Category> findCategoryLimitFromUser(int page, int size, String username) {
        User user = userRepo.findByUsername(username);
        List<Category> categoryList = new ArrayList<>();
        int length = (page+1) * size;
        if(user.getCategoryLikeList().size() <= (page+1) * size)   length = user.getCategoryLikeList().size();
        for(int index=page*size;index<length;index++){
            categoryList.add(user.getCategoryLikeList().get(index));
        }
        return categoryList;
    }

    @Override
    public List<Album> findAlbumLimitFromUser(int page, int size, String username) {
        User user = userRepo.findByUsername(username);
        List<Album> albumList = new ArrayList<>();
        int length = (page+1) * size;
        if(user.getAlbumList().size() <= (page+1) * size)   length = user.getAlbumList().size();
        for(int index=page*size;index<length;index++){
            albumList.add(user.getAlbumList().get(index));
        }
        return albumList;
    }

    @Override
    public List<Singer> findSingerLimitFromUser(int page, int size, String username) {
        User user = userRepo.findByUsername(username);
        List<Singer> singerList = new ArrayList<>();
        int length = (page+1) * size;
        if(user.getSingerList().size() <= (page+1) * size)   length = user.getSingerList().size();
        for(int index=page*size;index<length;index++){
            singerList.add(user.getSingerList().get(index));
        }
        return singerList;
    }

    @Override
    public User removeSongInUser(User user, int idSong) {
        for(Song songItem : user.getSongLike()){
            if(songItem.getId().equals(idSong)){
                user.getSongLike().remove(songItem);
                break;
            }
        }
        return user;
    }

    @Override
    public User removeAlbumInUser(User user, int idAlbum) {
        for(Album albumItem : user.getAlbumList()){
            if(albumItem.getId().equals(idAlbum)){
                user.getAlbumList().remove(albumItem);
                break;
            }
        }
        return user;
    }

    @Override
    public User removeSingerInUser(User user, int idSinger) {
        for(Singer singerItem : user.getSingerList()){
            if(singerItem.getId().equals(idSinger)){
                user.getSingerList().remove(singerItem);
                break;
            }
        }
        return user;
    }

    @Override
    public User removeCategoryInUser(User user, int idCategory) {
        for(Category categoryItem : user.getCategoryLikeList()){
            if(categoryItem.getId().equals(idCategory)){
                user.getCategoryLikeList().remove(categoryItem);
                break;
            }
        }
        return user;
    }


}
