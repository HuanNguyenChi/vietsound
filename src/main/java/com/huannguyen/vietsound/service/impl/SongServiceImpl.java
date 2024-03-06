package com.huannguyen.vietsound.service.impl;

import com.huannguyen.vietsound.entity.Song;
import com.huannguyen.vietsound.repo.SongRepo;
import com.huannguyen.vietsound.service.SongService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
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
}
