package com.huannguyen.vietsound.service;

import com.huannguyen.vietsound.entity.Role;

public interface RoleService {
    Role saveRole(Role role);
    Role findByName(String rolename);

}
