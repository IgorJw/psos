package com.example.psostest;

import com.example.psostest.Entity.Ent;
import com.example.psostest.Repository.EntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@SpringBootApplication
@RestController
public class PsosTestApplication implements CommandLineRunner {

	@Autowired
	EntRepository entRepository;
	public static void main(String[] args) {
		SpringApplication.run(PsosTestApplication.class, args);
	}

	@Override
	public void run(String... args)
	{
	}

	@GetMapping("/")
	@CrossOrigin(origins = "http://localhost:3000")
	public Map<String,String> home_page()
	{
		Ent xd = entRepository.findById(2).orElseThrow(()->new NoSuchElementException());
		HashMap<String,String> map = new HashMap<>();
		map.put("id",xd.getId().toString());
		return map;
	}

}
