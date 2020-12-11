package com.ottka.springpactconsumer;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;

import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,
		classes = SpringPactConsumerApplication.class)
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "reindeerProvider")
class SpringPactConsumerApplicationTests {

	@Autowired
	ReindeerClient reindeerClient;

	@Pact(provider = "reindeerProvider", consumer = "reindeerConsumer")
	public RequestResponsePact pactFetchReindeer(PactDslWithProvider builder) {
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		return builder
				.given("when Rudolph has id 1")
					.uponReceiving("GET with id")
					.path("/reindeers")
					.method("GET")
					.query("id=1")
					.willRespondWith()
						.status(200)
						.headers(headers)
						.body("{id: 1, name: 'Rudolph', title: 'the Red Nosed Reindeer'}")
				.toPact();
	}

	@Test
	@PactTestFor(pactMethod = "pactFetchReindeer")
	public void givenGet_whenSendRequest_shouldReturn200WithProperHeaderAndBody(MockServer mockServer) {
		reindeerClient.setBaseUrl(mockServer.getUrl());

		//When
		Reindeer rudolph = reindeerClient.fetchRudolph();

		//Then
		assertThat(rudolph.getName()).isEqualTo("Rudolph");
	}
}
