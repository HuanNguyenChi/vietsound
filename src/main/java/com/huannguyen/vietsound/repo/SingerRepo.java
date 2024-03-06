package com.huannguyen.vietsound.repo;

import com.huannguyen.vietsound.entity.Singer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingerRepo extends JpaRepository<Singer, Integer> {
    List<Singer> findAll();

    Singer findById(int id);

    @Query("select si from Singer si")
    List<Singer> findSingersLimit(Pageable pageable);
}
