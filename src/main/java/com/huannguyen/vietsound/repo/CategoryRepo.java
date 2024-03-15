package com.huannguyen.vietsound.repo;

import com.huannguyen.vietsound.entity.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {
    List<Category> findAll();
    Category findById(int id);
    @Query("select c from Category c")
    List<Category> findCategoriesLimit(Pageable pageable);
    void deleteById(int id);
}
