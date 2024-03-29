package com.huannguyen.vietsound.service.impl;

import com.huannguyen.vietsound.entity.Album;
import com.huannguyen.vietsound.entity.Category;
import com.huannguyen.vietsound.entity.Singer;
import com.huannguyen.vietsound.repo.AlbumRepo;
import com.huannguyen.vietsound.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumRepo albumRepo;

    @Override
    public List<Album> findAll() {
        return albumRepo.findAll();
    }

    @Override
    public Album findById(int id) {
        return albumRepo.findById(id);
    }

    @Override
    public List<Album> findBySingerOfAlbum(Singer singer) {
        return albumRepo.findBySingerOfAlbum(singer);
    }

    @Override
    public List<Album> findAlbumsLimit(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return albumRepo.findAlbumsLimit(pageable);
    }

    @Override
    public Album save(Album album) {
        return albumRepo.save(album);
    }

    @Override
    public void delete(int id) {
        albumRepo.deleteById(id);
    }

    @Override
    public List<Album> findAlbumsBySingerOfAlbumLimit(Singer singer,int page, int size) {
        List<Album> albumList = albumRepo.findAlbumsBySingerOfAlbum(singer);
        List<Album> res = new ArrayList<>();
        int end = (page+1) * size;
        if(albumList.size() < end) end = albumList.size();
        for(int index=page*size; index < end ; index++){
            if(index > albumList.size()) break;
            res.add(albumList.get(index));
        }
        return res;
    }

    @Override
    public List<Album> findAlbumsByCategoryOfAlbum(Category category, int page, int size) {
        List<Album> albumList = albumRepo.findAlbumsByCategoryOfAlbum(category);
        List<Album> res = new ArrayList<>();
        int end = (page+1) * size;
        if(albumList.size() < end) end = albumList.size();
        for(int index=page*size; index < end ; index++){
            if(index > albumList.size()) break;
            res.add(albumList.get(index));
        }
        return res;
    }
}
