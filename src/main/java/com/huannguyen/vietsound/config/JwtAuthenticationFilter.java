package com.huannguyen.vietsound.config;

import com.huannguyen.vietsound.service.CustomUserDetailSevice;
import com.huannguyen.vietsound.service.JWTSerive;
import com.huannguyen.vietsound.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private CustomUserDetailSevice customUserDetailSevice;
    @Autowired
    private JWTSerive jwtSerive;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt ;
        final  String username;

        if (StringUtils.isEmpty(authHeader) || !org.apache.commons.lang.StringUtils.startsWith(authHeader,"Bearer ")){
            filterChain.doFilter(request,response);
        }

        else if(authHeader != null && authHeader.startsWith("Bearer ")){
            jwt = authHeader.substring(7);
            username = jwtSerive.extractUsername(jwt);
            if(org.apache.commons.lang.StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = customUserDetailSevice.loadUserByUsername(username);

                if(jwtSerive.validateToken(jwt,userDetails)){
                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    securityContext.setAuthentication(token);
                    SecurityContextHolder.setContext(securityContext);
                }
            }
            filterChain.doFilter(request,response);
        }


    }

}
