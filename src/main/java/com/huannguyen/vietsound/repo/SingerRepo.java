package com.huannguyen.vietsound.repo;

import com.huannguyen.vietsound.entity.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingerRepo extends JpaRepository<Singer,Integer> {
}
