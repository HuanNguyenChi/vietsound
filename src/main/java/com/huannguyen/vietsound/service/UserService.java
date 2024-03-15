package com.huannguyen.vietsound.service;

import com.huannguyen.vietsound.entity.*;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    User update(User user);
    void delete(int id);
    void addToUser(String username, String rolename);
    List<User> findAll();
    User findById(int id);

    User findByUsername(String username);

    Optional<User> findUserByUsername(String username);

    boolean existsUserByEmail(String email);

    boolean existsUserByUsername(String username);

    boolean existsUserByUsernameAndPassword(String username, String password);

    List<Song> findSongLimitFromUser(int page, int size, String username);
    List<Category> findCategoryLimitFromUser(int page, int size, String username);
    List<Album> findAlbumLimitFromUser(int page, int size, String username);
    List<Singer> findSingerLimitFromUser(int page, int size, String username);

}
