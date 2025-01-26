package com.security.springSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSecurityController {

	@GetMapping("/meg")
	public String message() {
		return "Wellcom to  Spring Security";
	}

	@GetMapping("/contactg")
	public String contact() {
		return "ph + 9133774632";
	}

	@GetMapping("/welcome")
	public String welcome() {
		return "Hello Sundar....";
	}

}
