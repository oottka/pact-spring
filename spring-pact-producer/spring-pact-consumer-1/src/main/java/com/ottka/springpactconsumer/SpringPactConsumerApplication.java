package com.ottka.springpactconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringPactConsumerApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringPactConsumerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringPactConsumerApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Wishlist quote = restTemplate.getForObject(
					"http://localhost:8080/wishlist", Wishlist.class);
			log.info(quote.toString());
		};
	}
}
