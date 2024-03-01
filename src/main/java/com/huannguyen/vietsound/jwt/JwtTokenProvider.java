package com.huannguyen.vietsound.jwt;

import com.huannguyen.vietsound.entity.CustomUserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    // Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
    private final String JWT_SECRET = "NGUMOIHOCIT";
    //Thời gian có hiệu lực của chuỗi jwt
    private final long JWT_EXPIRATION = 604800000L;
    // tao chuoi jwt tu thong tin user
    public String gennerateToken(CustomUserDetail userDetail){
        Date now = new Date();
        Date expiration = new Date(now.getTime() + JWT_EXPIRATION);
        return null;
    }
}
