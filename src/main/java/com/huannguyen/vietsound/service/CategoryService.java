package com.huannguyen.vietsound.service;

import com.huannguyen.vietsound.entity.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(int id);
    List<Category> findCategoriesLimit(int page, int size);
}
