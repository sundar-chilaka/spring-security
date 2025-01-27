package com.security.SpringSecurity_Login_Register.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.SpringSecurity_Login_Register.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	
	public Customer findByEmail(String email);
}
