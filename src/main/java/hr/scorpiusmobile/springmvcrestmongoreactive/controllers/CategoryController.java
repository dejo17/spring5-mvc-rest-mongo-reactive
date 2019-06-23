package hr.scorpiusmobile.springmvcrestmongoreactive.controllers;

import hr.scorpiusmobile.springmvcrestmongoreactive.domain.Category;
import hr.scorpiusmobile.springmvcrestmongoreactive.repositories.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.reactivestreams.Publisher;


@RestController
@RequestMapping("/api/v1/categories")
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

    @PutMapping("/{id}")
    Mono<Category> updateCategory(@RequestBody Category category, @PathVariable String id){
        category.setId(id);
        return categoryRepository.save(category);
    }
    @PatchMapping("/{id}")
    Mono<Category> patchCategory(@RequestBody Category categoryToSave, @PathVariable String id){

        //*************************//
        //this belongs to service layer, which is here omitted for simplicity
        Category foundCategory = categoryRepository.findById(id).block();

        if(categoryToSave.getDescription()!=null && !foundCategory.getDescription().equalsIgnoreCase(categoryToSave.getDescription())){
            foundCategory.setDescription(categoryToSave.getDescription());
            return categoryRepository.save(foundCategory);
        }
        //************************//
        return Mono.just(foundCategory);
    }
}
