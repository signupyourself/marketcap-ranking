package com.webapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.client.RestTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
class WebAppTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTestClient restTestClient;

     @Test
     void testHomePageReturns200Ok() {
         assertThat(restTestClient.get()
                 .uri("http://localhost:%d/companies".formatted(port))
                 .exchange()
                 .expectStatus().isOk());
     };

     @Test
     void testHealthCheckReturns200Ok(){
         assertThat(restTestClient.get().uri("http://localhost:%d/health-check".formatted(port))
                 .exchange()
                 .expectStatus().isOk());

     }

     @Test
     public void testCountryPageReturns200Ok(){
         assertThat(restTestClient.get()
                 .uri("http://localhost:%d/countries".formatted(port))
                 .exchange()
                 .expectStatus().isOk());
     }

    @Test
    public void testMetricsPageReturns200Ok(){
        assertThat(restTestClient.get()
                .uri("http://localhost:%d/".formatted(port))
                .exchange()
                .expectBody(String.class)).isNotNull();
    }

    @Test
    public void testInvalidUriDoesNotReturn200Ok() {
        assertThat(restTestClient.get()
                .uri("http://localhost:%d/company/".formatted(port))
                .exchange()
                .expectStatus().isNotFound());
    }
}