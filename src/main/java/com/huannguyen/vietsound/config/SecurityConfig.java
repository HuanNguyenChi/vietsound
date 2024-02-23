package com.huannguyen.vietsound.config;

import com.huannguyen.vietsound.service.CustomUserDetailSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Autowired
    private CustomUserDetailSevice customUserDetailSevice;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests( (auth)->auth
                        .requestMatchers("/*").permitAll()
                        .requestMatchers("/admin/**")
                        .hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated())
                .formLogin( (login)->login
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/admin",true));

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/audio/**","/css/**","/fonts/**","/img/**","/js/**","/scss/**");
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}