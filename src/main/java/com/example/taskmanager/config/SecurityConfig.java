package com.example.taskmanager.config;

import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.spec.SecretKeySpec;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // login & register open
                        .anyRequest().authenticated()               // all other APIs require auth
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt());  // enable JWT auth

        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        String secret = "my-very-strong-secret-key-1234567890!@#";
        return NimbusJwtDecoder.withSecretKey(Keys.hmacShaKeyFor(secret.getBytes())).build();
    }

}
