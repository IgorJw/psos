package com.example.psostest;

import com.example.psostest.Entity.Ent;
import com.example.psostest.Repository.EntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
		Ent e = new Ent();
		e.setId(2);
		entRepository.save(e);
	}

	@GetMapping("/xd")
	public Integer home_page()
	{
		Ent e = entRepository.findById(2).orElseThrow(()->new NoSuchElementException());

		return e.getId();
	}

}
