package com.huannguyen.vietsound.contoller;

import com.huannguyen.vietsound.dto.JwtAuthenticationResponse;
import com.huannguyen.vietsound.dto.RefreshTokenRequest;
import com.huannguyen.vietsound.dto.SignupRequest;
import com.huannguyen.vietsound.entity.User;
import com.huannguyen.vietsound.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignupRequest signupRequest){
        return  ResponseEntity.ok(authenticationService.signup(signupRequest));
    }
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignupRequest signupRequest){
        return ResponseEntity.ok(authenticationService.signin(signupRequest));
    }
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}

