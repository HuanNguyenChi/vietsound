package com.huannguyen.vietsound.service.impl;

import com.huannguyen.vietsound.dto.JwtAuthenticationResponse;
import com.huannguyen.vietsound.dto.RefreshTokenRequest;
import com.huannguyen.vietsound.dto.SignupRequest;
import com.huannguyen.vietsound.entity.User;
import com.huannguyen.vietsound.repo.RoleRepo;
import com.huannguyen.vietsound.repo.UserRepo;
import com.huannguyen.vietsound.service.AuthenticationService;
import com.huannguyen.vietsound.service.JWTSerive;
import com.huannguyen.vietsound.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTSerive jwtSerive;

    @Override
    public User signup(SignupRequest signupRequest) {
        User user = new User();
        if (userService.existsUserByEmail(signupRequest.getEmail()) || userService.existsUserByUsername(signupRequest.getUsername())) {
            throw new UsernameNotFoundException("Username or email already exists");
        }
        user.setEmail(signupRequest.getEmail());
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.getRoles().add(roleRepo.findByName("ROLE_USER"));
        return userRepo.save(user);
    }

    @Override
    public JwtAuthenticationResponse signin(SignupRequest signupRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signupRequest.getUsername(), signupRequest.getPassword()));
        var user = userRepo.findUserByUsername(signupRequest.getUsername()).orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        var refreshToken = jwtSerive.generateRefershToken(new HashMap<>(), user);
        var token = jwtSerive.generateToken(user);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(token);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest request){
        String username = jwtSerive.extractUsername(request.getToken());
        User user = userRepo.findUserByUsername(username).orElseThrow();
        if(jwtSerive.validateToken(request.getToken(),user)){
            var jwt = jwtSerive.generateToken(user);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(request.getToken());
            return jwtAuthenticationResponse;
        }
        return  null;
    }
}
