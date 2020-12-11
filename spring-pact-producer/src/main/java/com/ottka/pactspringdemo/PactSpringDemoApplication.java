package com.ottka.pactspringdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;


@SpringBootApplication
@RestController
public class PactSpringDemoApplication {

	private Map<Long, Reindeer> reindeers = new LinkedHashMap<>();

	private static final Logger log = LoggerFactory.getLogger(PactSpringDemoApplication.class);

	public PactSpringDemoApplication() {
		reindeers.put(1L, new Reindeer(1L, "Rudolph", "the Red Nosed Reindeer"));
	}

	public static void main(String[] args) {
		SpringApplication.run(PactSpringDemoApplication.class, args);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/reindeers")
	public Reindeer getReindeer(@RequestParam(value = "id") Long id) {
		log.info("Getting reindeer with id: " + id);
		return reindeers.get(id);
	}
}
