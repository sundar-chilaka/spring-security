package com.security.SpringSecurity_Login_Register.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
//@WebFilter("/*")
public class JWTFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		 // 1. Get the token from the Authorization header
        String token = getTokenFromRequest(request);

        // 2. If no token found, respond with 401 Unauthorized
        if (token == null || token.isEmpty()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token not found");
            return;
        }

        // 3. If token is valid, extract username and set authentication context
        if (jwtService.validateToken(token)) {
            String username = jwtService.extractUsername(token);

            // You can also perform additional actions, such as checking roles, etc.
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null, null));

            filterChain.doFilter(request, response);  // Proceed to the requested endpoint
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
        }
    }
	 private String getTokenFromRequest(HttpServletRequest request) {
	        String header = request.getHeader("Authorization");
	        if (header != null && header.startsWith("Bearer ")) {
	            return header.substring(7);  // Remove the "Bearer " prefix
	        }
	        return null;
	    }
	

}
