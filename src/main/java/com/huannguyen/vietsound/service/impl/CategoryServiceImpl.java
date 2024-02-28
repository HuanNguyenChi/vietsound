package com.huannguyen.vietsound.service.impl;

import com.huannguyen.vietsound.entity.Category;
import com.huannguyen.vietsound.repo.CategoryRepo;
import com.huannguyen.vietsound.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryRepo.findById(id);
    }
}
