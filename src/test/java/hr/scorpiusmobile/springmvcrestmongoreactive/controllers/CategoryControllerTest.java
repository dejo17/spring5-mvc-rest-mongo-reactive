package hr.scorpiusmobile.springmvcrestmongoreactive.controllers;

import hr.scorpiusmobile.springmvcrestmongoreactive.domain.Category;
import hr.scorpiusmobile.springmvcrestmongoreactive.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.reactivestreams.Publisher;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

class CategoryControllerTest {

    WebTestClient webTestClient;

    CategoryRepository categoryRepository;

    CategoryController categoryController;

    @BeforeEach
    void setUp() {

        categoryRepository = Mockito.mock(CategoryRepository.class); //another way of defining mocks with Mockito
        categoryController = new CategoryController(categoryRepository);

        webTestClient = WebTestClient.bindToController(categoryController).build();

    }

    @Test
    void testGetAllCategories() {

        //this time BDD style syntax:
        BDDMockito.given(categoryRepository
                .findAll())
                .willReturn(Flux.just(Category.builder().description("Category1").build(),
                        Category.builder().description("Category2").build()));

        webTestClient.get().uri("/api/v1/categories/")
                .exchange()
                .expectBodyList(Category.class)
                .hasSize(2);
    }

    @Test
    void testGetCategoryByName() {
        //this time BDD style syntax:
        BDDMockito.given(categoryRepository
                .findById(anyString()))
                .willReturn(Mono.just(Category.builder().description("Category").build()));

        webTestClient.get().uri("/api/v1/categories/Category")
                .exchange()
                .expectBody(Category.class);
    }

    @Test
    void testCreateNewCategory() {

        BDDMockito.given(categoryRepository
                .saveAll(any(Publisher.class)))
                .willReturn(Flux.just(Category.builder().build())); //repository returns flux, controller returns empty mono

        Mono<Category> categoryToSave = Mono.just(Category.builder().description("Some category").build());
        webTestClient.post()
                .uri("/api/v1/categories/")
                .body(categoryToSave, Category.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    void testUpdateCategory() {

        BDDMockito.given(categoryRepository
                .save(any(Category.class)))
                .willReturn(Mono.just(Category.builder().build())); //repository returns flux, controller returns empty mono

        Mono<Category> categoryToSave = Mono.just(Category.builder().description("Some category").build());

        webTestClient.put()
                .uri("/api/v1/categories/1")
                .body(categoryToSave, Category.class)
                .exchange()
                .expectStatus()
                .isOk();
    }

}