package com.start.springboot.sample;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestApiControllerTests {
    @LocalServerPort
    int randomServerPort;

    @Test
    public void testRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        String testApiUrl = "http://localhost:" + randomServerPort;
        String apiResult = restTemplate.getForObject(testApiUrl, String.class);

        System.out.println("--------------- API Result ---------------");
        System.out.println(apiResult);
    }

    @Test
    public void testWebClient() {
//        WebClient webClient = WebClient.create("http://localhost:" + randomServerPort);
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:" + randomServerPort)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        String apiResult = webClient.get()
                .retrieve()
                .bodyToMono(String.class)
                .block();

//        Mono<UserDto> apiResult = webClient.get()
//                .retrieve()
//                .bodyToMono(UserDto.class);

//        Flux<UserDto> apiResult = webClient.get()
//                .retrieve()
//                .bodyToFlux(UserDto.class);

        System.out.println("--------------- API Result ---------------");
        System.out.println(apiResult);
    }
}
