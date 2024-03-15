package com.huannguyen.vietsound.service.impl;

import com.huannguyen.vietsound.entity.*;
import com.huannguyen.vietsound.repo.RoleRepo;
import com.huannguyen.vietsound.repo.UserRepo;
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
        for(int index=page*size;index<(page+1)*size;index++){
            songList.add(user.getSongLike().get(index));
        }
        return songList;
    }

    @Override
    public List<Category> findCategoryLimitFromUser(int page, int size, String username) {
        User user = userRepo.findByUsername(username);
        List<Category> categoryList = new ArrayList<>();
        for(int index=page*size;index<(page+1)*size;index++){
            categoryList.add(user.getCategoryLikeList().get(index));
        }
        return categoryList;
    }

    @Override
    public List<Album> findAlbumLimitFromUser(int page, int size, String username) {
        User user = userRepo.findByUsername(username);
        List<Album> albumList = new ArrayList<>();
        for(int index=page*size;index<(page+1)*size;index++){
            albumList.add(user.getAlbumList().get(index));
        }
        return albumList;
    }

    @Override
    public List<Singer> findSingerLimitFromUser(int page, int size, String username) {
        User user = userRepo.findByUsername(username);
        List<Singer> singerList = new ArrayList<>();
        for(int index=page*size;index<(page+1)*size;index++){
            singerList.add(user.getSingerList().get(index));
        }
        return singerList;
    }

}
