package hr.scorpiusmobile.springmvcrestmongoreactive.controllers;

import hr.scorpiusmobile.springmvcrestmongoreactive.domain.Category;
import hr.scorpiusmobile.springmvcrestmongoreactive.repositories.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


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

}
