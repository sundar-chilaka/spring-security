package com.security.springSecurity.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.SneakyThrows;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	
	
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
