package com.huannguyen.vietsound.service.impl;

import com.huannguyen.vietsound.entity.Album;
import com.huannguyen.vietsound.entity.Category;
import com.huannguyen.vietsound.entity.Singer;
import com.huannguyen.vietsound.entity.Song;
import com.huannguyen.vietsound.repo.SongRepo;
import com.huannguyen.vietsound.service.SongService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SongServiceImpl implements SongService {
    @Autowired
    private SongRepo songRepo;

    @Override
    public List<Song> findAll() {
        return songRepo.findAll();
    }

    @Override
    public Song findById(int id) {
        return songRepo.findById(id);
    }

    @Override
    public List<Song> findSongsLimit(int page,int size) {
        Pageable pageable = PageRequest.of(page,size);
        return songRepo.findSongsLimit(pageable);
    }

    @Override
    public Song save(Song song) {
        return songRepo.save(song);
    }

    @Override
    public void delete(int id) {
        songRepo.deleteSongById(id);
    }

    @Override
    public List<Song> findSongsBySingerOfSongLimit(Singer singer, int page, int size) {
        List<Song> songList = songRepo.findSongsBySingerOfSong(singer);
        List<Song> res = new ArrayList<>();
        int cnt = 0;
        int end = (page+1) * size;
        if(songList.size() < end) end = songList.size();
        for(int index=page*size; index < end ; index++){
            res.add(songList.get(index));
        }
        return res;
    }

    @Override
    public List<Song> findSongsByCategoryOfSongLimit(Category category, int page, int size) {
        List<Song> songList = songRepo.findSongsByCategoryOfSong(category);
        List<Song> res = new ArrayList<>();
        int end = (page+1) * size;
        if(songList.size() < end) end = songList.size();
        for(int index=page*size; index < end ; index++){
            res.add(songList.get(index));
        }
        return res;
    }

    @Override
    public List<Song> findSongsByAlbumLimit(Album album, int page, int size) {
        List<Song> songList = songRepo.findSongsByAlbum(album);
        List<Song> res = new ArrayList<>();
        int end = (page+1) * size;
        if(songList.size() < end) end = songList.size();
        for(int index=page*size; index < end ; index++){
            res.add(songList.get(index));
        }
        return res;
    }
}
