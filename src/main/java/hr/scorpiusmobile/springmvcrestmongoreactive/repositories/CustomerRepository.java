package hr.scorpiusmobile.springmvcrestmongoreactive.repositories;

import hr.scorpiusmobile.springmvcrestmongoreactive.domain.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
}
