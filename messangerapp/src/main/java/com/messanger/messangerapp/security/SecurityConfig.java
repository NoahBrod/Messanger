package com.messanger.messangerapp.security;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                (requests) -> requests
                        .requestMatchers("/", "/home", "/register", "/login*").permitAll()
                        .requestMatchers("/profile", "/messages").hasRole("USER")
                        .anyRequest().authenticated())
                .formLogin((form) -> form
                        .loginPage("/login").defaultSuccessUrl("/messanger", true)

                        .permitAll().failureHandler(new AuthenticationFailureHandler() {

                            @Override
                            public void onAuthenticationFailure(HttpServletRequest request,
                                    HttpServletResponse response, AuthenticationException exception)
                                    throws IOException, ServletException {
                                response.sendRedirect("/login?error" + URLEncoder
                                        .encode("Invalide Username or Password.", StandardCharsets.UTF_8.toString()));
                            }

                        }))
                .logout((logout) -> logout.logoutSuccessUrl("/login?logout").permitAll())
                .rememberMe((token) -> token
                        .tokenValiditySeconds(300))
                        .csrf((csrf) -> csrf.disable()).cors((cors) -> cors.disable());

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }
}
