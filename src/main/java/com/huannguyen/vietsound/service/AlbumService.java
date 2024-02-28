package com.huannguyen.vietsound.service;

import com.huannguyen.vietsound.entity.Album;
import com.huannguyen.vietsound.entity.Singer;

import java.util.List;

public interface AlbumService {
    List<Album> findAll();
    Album findById(int id);
    List<Album> findBySingerOfAlbum(Singer singer);
}
