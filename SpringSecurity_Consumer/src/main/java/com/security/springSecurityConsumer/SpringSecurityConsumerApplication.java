package com.security.springSecurityConsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringSecurityConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityConsumerApplication.class, args);
		// this logic is not working

//		String apiUrl="http://localhost:8080/meg";
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<String> forEntity=restTemplate.getForEntity(apiUrl, String.class);
//		System.out.println(forEntity.getBody());

		
		//calling to RestTemplate
		String apiUrl = "http://localhost:8080/meg";

		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth("sundar", "Sundar@123");

		HttpEntity<String> reqEntity = new HttpEntity<String>(headers);

		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<String> forEntity=restTemplate.getForEntity(apiUrl, String.class);

		ResponseEntity<String> resEntity = restTemplate
											.exchange(apiUrl, HttpMethod.GET, reqEntity, String.class);
		System.out.println(resEntity.getBody());
		
		//calling to WebClient
		
//		webClient.get()
//				.uri("http://localhost:8080/meg")
//				.header(Authorization,"Basic kojfdnibh3qw4093r438342");
//				.retrieve()
//				.bodyTOMono(String.class)
//				.block();
	}
}
