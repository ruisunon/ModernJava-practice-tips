package com.rydata.reactive;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest
class CustomerControllerTest {

    @Autowired
    WebTestClient webTestClient;
    @MockBean
    CustomerRepository customerRepository;

    @Test
    void testGetCustomer() {
        Flux<Customer> customerFlux = Flux.just(new Customer(Long.valueOf(1), "Foo"), new Customer(Long.valueOf(2), "Bar"));
        Mockito.when(customerRepository.findAll()).thenReturn(customerFlux);
        webTestClient.get().uri("/customer").accept(MediaType.APPLICATION_JSON).exchange()
                .expectStatus().isOk().expectBody().jsonPath("$").isArray().jsonPath("$[0].id").isEqualTo(1).jsonPath("$[0].name").isEqualTo("Fool");
    }

    @Test
    void testPostCustomer(){
        Customer customer=new Customer(Long.valueOf(1), "Foo");
        Mono<Customer> monoC= Mono.just(customer);
        Mockito.when(customerRepository.save(customer)).thenReturn(monoC);
        webTestClient.post().uri("/customer").contentType(MediaType.APPLICATION_JSON)
                .body(monoC, Customer.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Customer.class).isEqualTo(customer);
    }
}