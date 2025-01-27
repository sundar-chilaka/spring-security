package com.security.SpringSecurity_Login_Register.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.security.SpringSecurity_Login_Register.service.CustomerService;

import lombok.SneakyThrows;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private CustomerService customerService;
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new 
				DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(encoder());
		authProvider.setUserDetailsService(customerService);
		return authProvider;
	}
	@Bean
	@SneakyThrows
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
		return configuration.getAuthenticationManager();
	}
	
	  @Bean
	    @SneakyThrows
	    public SecurityFilterChain filterChain(HttpSecurity http) {
	        http.authorizeHttpRequests((req) -> {
	            req.requestMatchers("/security/register", "/security/login")
	                .permitAll()   // Allow access to register and login endpoints for anyone
	                .anyRequest()
	                .authenticated(); // Secure other endpoints that require authentication
	        });

	        // Disable CSRF for non-browser clients, or if using stateless API
	        return http.csrf().disable().build();
}}
