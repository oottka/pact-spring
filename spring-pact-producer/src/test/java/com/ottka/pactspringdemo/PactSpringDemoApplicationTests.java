package com.ottka.pactspringdemo;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;
import com.atlassian.oai.validator.pact.PactProviderValidationResults;
import com.atlassian.oai.validator.pact.PactProviderValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Provider("reindeerProvider")
@PactFolder("pacts")
class PactSpringDemoApplicationTests {

	@TestTemplate
	@ExtendWith(PactVerificationSpringProvider.class)
	void pactVerificationTestTemplate(PactVerificationContext context) throws IOException {
		// Instrument the test
		context.verifyInteraction();

		//Check the pact against the OpenAPI spec
		final PactProviderValidator validator;
		validator = PactProviderValidator
				.createFor(getClass().getClassLoader().getResource("openapi/openapi.yml").toString())
				.withConsumer("reindeerConsumer", new ClassPathResource("pacts/reindeerConsumer-reindeerProvider.json").getURL())
				.build();

		final PactProviderValidationResults results = validator.validate();

		assertThat(results.getValidationFailureReport(), results.hasErrors(), is(false));
	}

	@BeforeEach
	void before(PactVerificationContext context) {
		context.setTarget(new HttpTestTarget("localhost", 8080, "/"));
	}

	@State("GET Rudolph")
	public void fetchReindeer() {
	}
}
