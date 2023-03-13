package com.example.psostest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PsosTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PsosTestApplication.class, args);
	}

	@GetMapping("/home")
	public static String home_page()
	{
		return "Prosze działaj bardzo";
	}

}
