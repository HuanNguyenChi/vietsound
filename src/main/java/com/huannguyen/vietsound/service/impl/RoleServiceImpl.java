package com.huannguyen.vietsound.service.impl;

import com.huannguyen.vietsound.entity.Role;
import com.huannguyen.vietsound.repo.RoleRepo;
import com.huannguyen.vietsound.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }
}
