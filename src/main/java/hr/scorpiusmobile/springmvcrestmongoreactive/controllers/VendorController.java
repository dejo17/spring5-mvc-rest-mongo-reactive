package hr.scorpiusmobile.springmvcrestmongoreactive.controllers;

import hr.scorpiusmobile.springmvcrestmongoreactive.domain.Vendor;
import hr.scorpiusmobile.springmvcrestmongoreactive.repositories.VendorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/vendors/")
public class VendorController {

    private final VendorRepository vendorRepository; //skipping the service layer in this example

    public VendorController(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @GetMapping
    public Flux<Vendor> getAllVendors(){
        return vendorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Vendor> getVendorById(@PathVariable String id){
        return vendorRepository.findById(id);
    }

}
