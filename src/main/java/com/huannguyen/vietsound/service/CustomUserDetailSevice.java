package com.huannguyen.vietsound.service;

import com.huannguyen.vietsound.entity.CustomUserDetail;
import com.huannguyen.vietsound.entity.Role;
import com.huannguyen.vietsound.entity.User;
import com.huannguyen.vietsound.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailSevice implements UserDetailsService  {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadUser(username);
    }

    private UserDetails loadUser(String username){
        User user = userService.findByUsername(username);
        if(user == null) throw new UsernameNotFoundException("Not found");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        List<Role> roles = user.getRoles();
        for(Role item : roles){
            authorities.add(new SimpleGrantedAuthority(item.getName()));
        }
        return new CustomUserDetail(user,authorities);
    }
}
