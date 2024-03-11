package com.huannguyen.vietsound.service;

import com.huannguyen.vietsound.entity.Singer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SingerService {
    List<Singer> findAll();

    Singer findById(int id);

    List<Singer> findSingersLimit(int page, int size);
}
