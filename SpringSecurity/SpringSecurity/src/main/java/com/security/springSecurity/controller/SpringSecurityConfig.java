package com.security.springSecurity.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.SneakyThrows;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		
		
		UserDetails u1 = 
				User.withDefaultPasswordEncoder()
					.username("Sundar")	
					.password("Sundar@123")
					.build();
		UserDetails u2 = 
				User.withDefaultPasswordEncoder()
					.username("ADMIN")	
					.password("Admin@123")
					.build();
		UserDetails u3= 
				User.withDefaultPasswordEncoder()
					.username("USER")	
					.password("User@123")
					.build();
		return new InMemoryUserDetailsManager(u1,u2,u3);
	}
	
	@Bean
	@SneakyThrows
	public SecurityFilterChain securityFilterChain(HttpSecurity http){
		http.authorizeHttpRequests((req)->{
		req.requestMatchers("/welcome")
			.permitAll()
			.anyRequest()
			.authenticated()
			;
		}).httpBasic((Customizer.withDefaults()))
		.formLogin(Customizer.withDefaults());
		return http.build();
	}

}
