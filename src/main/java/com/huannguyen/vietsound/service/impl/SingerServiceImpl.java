package com.huannguyen.vietsound.service.impl;

import com.huannguyen.vietsound.entity.Singer;
import com.huannguyen.vietsound.repo.SingerRepo;
import com.huannguyen.vietsound.service.SingerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    @Override
    public List<Singer> findSingersLimit(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return singerRepo.findSingersLimit(pageable);
    }
}
