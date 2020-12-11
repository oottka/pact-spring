package com.ottka.springpactconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@SpringBootApplication()
public class SpringPactConsumerApplication implements CommandLineRunner {

	@Autowired
	ReindeerClient reindeerClient;

	private static final Logger log = LoggerFactory.getLogger(SpringPactConsumerApplication.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SpringPactConsumerApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8083"));
		app.run(args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Override
	public void run(String... args) throws Exception {
		//reindeerClient.setBaseUrl("http://localhost:8080");
		//Reindeer rudolph = reindeerClient.fetchRudolph();
		//log.info("Saved rudolph: " + rudolph.toString());
	}
}
