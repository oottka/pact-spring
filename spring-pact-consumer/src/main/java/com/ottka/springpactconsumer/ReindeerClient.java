package com.ottka.springpactconsumer;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ReindeerClient {

    private String baseUrl;

    public Reindeer fetchRudolph() {
        return fetchReindeer(1L);
    }

    public Reindeer fetchReindeer(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(
                baseUrl + "/reindeers?id=" + id.toString(), Reindeer.class);
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
