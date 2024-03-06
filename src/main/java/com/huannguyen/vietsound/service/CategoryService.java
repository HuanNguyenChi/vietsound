package com.huannguyen.vietsound.service;

import com.huannguyen.vietsound.entity.Category;
import com.huannguyen.vietsound.entity.Singer;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(int id);
    List<Category> findCategoriesLimit(int page,int size);
}
