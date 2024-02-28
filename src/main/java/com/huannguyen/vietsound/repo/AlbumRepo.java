package com.huannguyen.vietsound.repo;

import com.huannguyen.vietsound.entity.Album;
import com.huannguyen.vietsound.entity.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepo extends JpaRepository<Album,Integer> {
    List<Album> findAll();
    Album findById(int id);
    List<Album> findBySingerOfAlbum(Singer singer);
}
