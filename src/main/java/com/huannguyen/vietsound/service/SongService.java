package com.huannguyen.vietsound.service;

import com.huannguyen.vietsound.entity.Song;

import java.util.List;

public interface SongService {
    List<Song> findAll();
    Song findById(int id);
}
