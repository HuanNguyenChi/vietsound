package com.huannguyen.vietsound.service;

import com.huannguyen.vietsound.entity.User;

public interface UserService {
    User saveUser(User user);
    void addToUser(String username, String rolename);
}
