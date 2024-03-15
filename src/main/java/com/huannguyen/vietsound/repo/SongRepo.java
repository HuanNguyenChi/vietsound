package com.huannguyen.vietsound.repo;

import com.huannguyen.vietsound.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SongRepo extends JpaRepository<Song,Integer> {
    List<Song> findAll();
    Song findById(int id);
    // day la JPQL
    @Query("select s from Song s")
    List<Song> findSongsLimit(Pageable pageable);

    @Transactional
    void deleteSongById(int id);
}
