package com.huannguyen.vietsound.repo;

import com.huannguyen.vietsound.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
//    Integer save(User user);
    User findById(int id);
    User findByUsername(String username);
    Optional<User> findUserByUsername(String username);
    boolean existsUserByEmail(String email);
    boolean existsUserByUsername(String username);
    boolean existsUserByUsernameAndPassword(String username,String password);
    void deleteById(int id);
}
