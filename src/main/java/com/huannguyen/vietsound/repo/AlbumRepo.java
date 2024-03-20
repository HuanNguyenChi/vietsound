package com.huannguyen.vietsound.repo;

import com.huannguyen.vietsound.entity.Album;
import com.huannguyen.vietsound.entity.Singer;
import com.huannguyen.vietsound.entity.Song;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepo extends JpaRepository<Album,Integer> {
    List<Album> findAll();
    Album findById(int id);
    List<Album> findBySingerOfAlbum(Singer singer);
    @Query("select a from Album a")
    List<Album> findAlbumsLimit(Pageable pageable);
    void deleteById(int id);
    @Query(value = "select a from Album  a where a.singerOfAlbum = :singer")
    List<Album> findAlbumsBySingerOfAlbum(Singer singer);
}
