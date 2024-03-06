package com.huannguyen.vietsound.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthenticationResponse {
    private String token;
    private String refreshToken;
    private String username;
    private String password;
    private List<String> roles;
}
