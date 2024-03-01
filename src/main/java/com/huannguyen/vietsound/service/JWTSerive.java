package com.huannguyen.vietsound.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTSerive {
    String extractUsername(String token);
    String generateToken(UserDetails userDetails);
    boolean validateToken(String token, UserDetails userDetails);
    String generateRefershToken(Map<String,Object> extraClaims, UserDetails userDetails);
}
