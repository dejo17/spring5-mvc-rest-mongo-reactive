package hr.scorpiusmobile.springmvcrestmongoreactive.controllers;

import hr.scorpiusmobile.springmvcrestmongoreactive.domain.Vendor;
import hr.scorpiusmobile.springmvcrestmongoreactive.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

class VendorControllerTest {

    VendorRepository vendorRepository;
    VendorController vendorController;
    WebTestClient webTestClient;

    @BeforeEach
    void setUp() {

        vendorRepository = Mockito.mock(VendorRepository.class);
        vendorController = new VendorController(vendorRepository);

        webTestClient = WebTestClient.bindToController(vendorController).build();
    }

    @Test
    void getAllVendors() {

        BDDMockito.given(vendorRepository.findAll()).willReturn(Flux.just(Vendor.builder().build(),Vendor.builder().build()));

        webTestClient.get().uri("/api/v1/vendors/")
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(2);
    }

    @Test
    void getVendorById() {

        BDDMockito.given(vendorRepository.findById(anyString())).willReturn(Mono.just(Vendor.builder().id("1234").build()));

        webTestClient.get().uri("/api/v1/vendors/1234")
                .exchange()
                .expectBody(Vendor.class);


    }
}