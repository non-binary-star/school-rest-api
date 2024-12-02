package com.example.demo.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class JwtAuthFilter extends AbstractAuthenticationProcessingFilter {

    private final JwtService jwtService;
    private final InMemoryUserDetailsManager userDetailsService;

    public JwtAuthFilter(RequestMatcher requestMatcher, AuthenticationManager authenticationManager, final JwtService jwtService, final InMemoryUserDetailsManager userDetailsService) {
        super(requestMatcher, authenticationManager);
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            String userEmail = jwtService.extractUsername(jwt);

            if (userEmail != null && jwtService.isTokenValid(jwt, userDetailsService.loadUserByUsername(userEmail))) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                return authenticationToken;
            }
        }
        return null;
    }
}
