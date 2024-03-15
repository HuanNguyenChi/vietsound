package com.huannguyen.vietsound.service.impl;

import com.huannguyen.vietsound.entity.Category;
import com.huannguyen.vietsound.repo.CategoryRepo;
import com.huannguyen.vietsound.service.CategoryService;
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

    @Override
    public List<Category> findCategoriesLimit(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return categoryRepo.findCategoriesLimit(pageable);
    }

    @Override
    public void delete(int id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryRepo.save(category);
    }

}
