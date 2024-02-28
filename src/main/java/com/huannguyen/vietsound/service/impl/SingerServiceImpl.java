package com.huannguyen.vietsound.service.impl;

import com.huannguyen.vietsound.entity.Singer;
import com.huannguyen.vietsound.repo.SingerRepo;
import com.huannguyen.vietsound.repo.SongRepo;
import com.huannguyen.vietsound.service.SingerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SingerServiceImpl implements SingerService {
    @Autowired
    private SingerRepo singerRepo;

    @Override
    public List<Singer> findAll() {
        return singerRepo.findAll();
    }

    @Override
    public Singer findById(int id) {
        return singerRepo.findById(id);
    }
}
