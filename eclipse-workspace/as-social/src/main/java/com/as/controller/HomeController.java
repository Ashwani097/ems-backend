package com.as.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping
	public String homeControllerHandler() {
		return "This is home Controller";
	}
	@GetMapping("/home")
	public String homeControllerHandler2() {
		return "This is home Controller2";
	}
	
	
	

}
