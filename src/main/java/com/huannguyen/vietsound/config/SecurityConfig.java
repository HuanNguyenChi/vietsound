package com.huannguyen.vietsound.config;


import com.huannguyen.vietsound.service.CustomUserDetailSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // Cài đặt Spring Security để phân quyền khi đăng nhập.

    @Autowired
    private CustomUserDetailSevice customUserDetailSevice;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // Các trang không yêu cầu login, không yêu cầu role nào
//        http.authorizeRequests().antMatchers("/", "/logout").permitAll();
        // Các trang chỉ truy cập được khi là khách
        http.authorizeRequests().antMatchers("/login", "/register").access("isAnonymous()");
        // Các trang yêu cầu phải login với vai trò ROLE_USER hoặc ROLE_ADMIN.
        http.authorizeRequests().antMatchers("/logout").access("hasAnyRole('ROLE_USER ', 'ROLE_ADMIN')");
        // Các trang yêu cầu phải login với vai trò ROLE_ADMIN
        http.authorizeRequests().antMatchers(
                "/admin/**"
        ).access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers(
                "/user/**"
        ).access("hasAnyRole('ROLE_USER','ROLE_ADMIN')");
        // Các trang yêu cầu phải login với vai trò ROLE_USER

        // Khi truy cập trang không được phép.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
        //truy cap moi luc
        http.authorizeRequests().anyRequest().permitAll();
        // Cấu hình cho Login Form.
        http.authorizeRequests().and().formLogin()
                // Submit URL của trang login
                .loginProcessingUrl("/j_spring_security_check") // Submit URL co sẵn
                .loginPage("/login") // trang login
                .defaultSuccessUrl("/") // login thành công
                .failureUrl("/login?error=true") // login thất bại
                .usernameParameter("username")
                .passwordParameter("password")
                // Cấu hình cho Logout Page.
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");


        // Cấu hình Remember Me.
        http.authorizeRequests().and()
                .rememberMe().tokenRepository(this.persistentTokenRepository())
                .tokenValiditySeconds(86400); // = 24h
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Cài đặt dịch vụ để tìm kiếm User trong Database.
        // Và cài đặt PasswordEncoder.
        auth.userDetailsService(customUserDetailSevice).passwordEncoder(passwordEncoder());
//        auth.userDetailsService(customUserDetailSevice);
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        // lưu token trong memory
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
        return memory;
    }
}
