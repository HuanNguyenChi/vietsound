package com.huannguyen.vietsound.service.impl;

import com.huannguyen.vietsound.entity.Album;
import com.huannguyen.vietsound.entity.Singer;
import com.huannguyen.vietsound.repo.AlbumRepo;
import com.huannguyen.vietsound.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}
