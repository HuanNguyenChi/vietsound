package com.huannguyen.vietsound.service.impl;

import com.huannguyen.vietsound.entity.Album;
import com.huannguyen.vietsound.entity.Singer;
import com.huannguyen.vietsound.repo.AlbumRepo;
import com.huannguyen.vietsound.service.AlbumService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
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
}
