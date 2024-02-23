package com.huannguyen.vietsound.repo;

import com.huannguyen.vietsound.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepo extends JpaRepository<Song,Integer> {
}
