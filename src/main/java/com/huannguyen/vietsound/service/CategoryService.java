package com.huannguyen.vietsound.service;

import com.huannguyen.vietsound.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(int id);
}
