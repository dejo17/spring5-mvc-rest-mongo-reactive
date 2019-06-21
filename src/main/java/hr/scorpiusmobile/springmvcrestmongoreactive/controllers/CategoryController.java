package hr.scorpiusmobile.springmvcrestmongoreactive.controllers;

import hr.scorpiusmobile.springmvcrestmongoreactive.domain.Category;
import hr.scorpiusmobile.springmvcrestmongoreactive.repositories.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.reactivestreams.Publisher;


@RestController
@RequestMapping("/api/v1/categories/")
public class CategoryController {

    private final CategoryRepository categoryRepository; //skipping service layer in this example

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public Flux<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @GetMapping("/{description}")
    public Mono<Category> getCategoryByName(@PathVariable String description){
        return categoryRepository.findByDescription(description);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Mono<Void> createNewUser(@RequestBody Publisher<Category> categoryStream){  //mono and flux are publishers
        return categoryRepository.saveAll(categoryStream).then();  //then returns Mono<void>
    }

}
