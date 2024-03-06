package com.huannguyen.vietsound.service;

import com.huannguyen.vietsound.entity.Singer;

import java.util.List;

public interface SingerService {
    List<Singer> findAll();
    Singer findById(int id);
    List<Singer> findSingersLimit(int page,int size);
}
