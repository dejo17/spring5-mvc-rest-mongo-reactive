package hr.scorpiusmobile.springmvcrestmongoreactive.repositories;

import hr.scorpiusmobile.springmvcrestmongoreactive.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {
    public Mono<Category> findByDescription(String description);
}
