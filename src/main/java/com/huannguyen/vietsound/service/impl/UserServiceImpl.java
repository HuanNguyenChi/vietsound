package com.huannguyen.vietsound.service.impl;

import com.huannguyen.vietsound.entity.Role;
import com.huannguyen.vietsound.entity.User;
import com.huannguyen.vietsound.repo.RoleRepo;
import com.huannguyen.vietsound.repo.UserRepo;
import com.huannguyen.vietsound.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public void addToUser(String username, String rolename) {
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(rolename);
        try {
            user.getRoles().add(role);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

}
