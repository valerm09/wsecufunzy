package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Vendor {

	@GetMapping("vendor/")
	public String index() {
		return "Soda soda WSECU!";
	}

	@PostMapping("vendor/")
	public String buySoda() {
		return "Soda soda WSECU!";
	}


}
