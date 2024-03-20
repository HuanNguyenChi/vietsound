package com.huannguyen.vietsound.service;

import com.huannguyen.vietsound.entity.Singer;
import com.huannguyen.vietsound.entity.Song;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SongService {
    List<Song> findAll();
    Song findById(int id);
    // day la JPQL
    List<Song> findSongsLimit(int page, int size);
    Song save(Song song);
    void delete(int id);
    List<Song> findSongsBySingerOfSongLimit(Singer singer,int page, int size);
}
