package com.security.springSecurity.controller;

import org.springframework.context.annotation.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSecurityController {

	@GetMapping("/meg")
	@PreAuthorize("hasRole('ADMIN')")
	public String message() {
		return "Wellcom to  Spring Security";
	}

	@GetMapping("/contactg")
	@PreAuthorize("hasRole('Sundar')")
	public String contact() {
		return "ph + 9133774632";
	}

	@GetMapping("/welcome")
	@PreAuthorize("hasRole('USER')")
	public String welcome() {
		return "Hello Sundar....";
	}

}
