package com.security.SpringSecurity_Login_Register.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.SpringSecurity_Login_Register.entity.Customer;
//import com.security.SpringSecurity_Login_Register.jwt.JwtResponse;
import com.security.SpringSecurity_Login_Register.jwt.JwtService;
import com.security.SpringSecurity_Login_Register.service.CustomerService;

@RestController
@RequestMapping("/security")
public class CustomerRestController {

	
	@Autowired
	private JwtService jwt;
	
	@Autowired
	private CustomerService customerService;
//	@Autowired
//	private JwtResponse jwtResponse;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<String>login(@RequestBody Customer customer){
		UsernamePasswordAuthenticationToken authenticationToken  = new
				UsernamePasswordAuthenticationToken(customer.getEmail(), customer.getPassword());
		try {
		//Verifying login details valid or not
		Authentication authenticate=authenticationManager.authenticate(authenticationToken );
		
		boolean status = authenticate.isAuthenticated();
		
		if(status) {
			//Generate JWT and send to Client
			
			 String token = jwt.generateToken(customer.getEmail());

			return new ResponseEntity<>(token, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Failed to authenticate", HttpStatus.BAD_REQUEST);
		}}catch (Exception e) {
			 // If any exception occurs (e.g., invalid credentials), return a failure message
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<String>rigisterCustomer(@RequestBody Customer customer){
		boolean status=customerService.saveCustomer(customer);
		if(status) {
			return new ResponseEntity<>("Success", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
}
