package com.huannguyen.vietsound.service;

import com.huannguyen.vietsound.dto.JwtAuthenticationResponse;
import com.huannguyen.vietsound.dto.RefreshTokenRequest;
import com.huannguyen.vietsound.dto.SignupRequest;
import com.huannguyen.vietsound.entity.User;

public interface AuthenticationService {
    User signup(SignupRequest signupRequest);
    JwtAuthenticationResponse signin(SignupRequest signupRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest request);
}
