package com.huannguyen.vietsound.service.impl;

import com.huannguyen.vietsound.repo.AlbumRepo;
import com.huannguyen.vietsound.service.AlbumService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumRepo albumRepo;
}
