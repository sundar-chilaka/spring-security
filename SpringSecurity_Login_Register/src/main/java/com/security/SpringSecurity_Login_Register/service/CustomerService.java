package com.security.SpringSecurity_Login_Register.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.SpringSecurity_Login_Register.entity.Customer;
import com.security.SpringSecurity_Login_Register.repository.CustomerRepository;

@Service
public class CustomerService implements UserDetailsService{

	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomerRepository repository;
	
	
	public boolean saveCustomer(Customer customer) {
		String encodepassword = passwordEncoder.encode(customer.getPassword());
		customer.setPassword(encodepassword);
		Customer saveCustomer =repository.save(customer);
		return saveCustomer.getCid()!=null;
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Customer c = repository.findByEmail(email);
		
		return new User(c.getEmail(), c.getPassword(), Collections.emptyList());
	}
}
