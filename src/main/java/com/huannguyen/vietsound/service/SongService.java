package com.huannguyen.vietsound.service;

import com.huannguyen.vietsound.entity.Song;

import java.awt.print.Pageable;
import java.util.List;

public interface SongService {
    List<Song> findAll();
    Song findById(int id);
    List<Song> findSongsLimit(int page,int size);
}
