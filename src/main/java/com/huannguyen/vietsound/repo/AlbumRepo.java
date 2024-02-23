package com.huannguyen.vietsound.repo;

import com.huannguyen.vietsound.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepo extends JpaRepository<Album,Integer> {
}
