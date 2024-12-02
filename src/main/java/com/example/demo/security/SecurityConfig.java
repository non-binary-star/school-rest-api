package com.example.demo.security;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtService jwtService;
    private final AuthenticationConfiguration authConf;

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                new User("admin", passwordEncoder().encode("admin"), List.of(new SimpleGrantedAuthority("ADMIN"))),
                new User("teacher", passwordEncoder().encode("teacher"), List.of(new SimpleGrantedAuthority("TEACHER"))),
                new User("student", passwordEncoder().encode("student"), List.of(new SimpleGrantedAuthority("STUDENT")))
        );
    }

    @Bean
    @SneakyThrows
    public AuthenticationManager authenticationManager() {
        return authConf.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable();
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/auth/**").permitAll()
//                        .anyRequest().permitAll()
//                )
//                .sessionManagement(h -> h.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .anonymous().disable()
//                .logout().disable()
//                .addFilterBefore(new JwtAuthFilter(AntPathRequestMatcher.antMatcher("/api/**"), authenticationManager(), jwtService, inMemoryUserDetailsManager()), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
