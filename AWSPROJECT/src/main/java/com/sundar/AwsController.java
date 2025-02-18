package com.sundar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aws")
public class AwsController {

	@GetMapping("/meg")
	public String massage() {
		return "Hey this is Sundar...!";
	}
}
