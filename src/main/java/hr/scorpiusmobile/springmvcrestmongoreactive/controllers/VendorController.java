package hr.scorpiusmobile.springmvcrestmongoreactive.controllers;

import hr.scorpiusmobile.springmvcrestmongoreactive.domain.Vendor;
import hr.scorpiusmobile.springmvcrestmongoreactive.repositories.VendorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.reactivestreams.Publisher;

@RestController
@RequestMapping("/api/v1/vendors/")
public class VendorController {

    private final VendorRepository vendorRepository; //skipping the service layer in this example

    public VendorController(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @GetMapping
    public Flux<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Vendor> getVendorById(@PathVariable String id) {
        return vendorRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> createNewVendor(@RequestBody Publisher<Vendor> vendor) {
        return vendorRepository.saveAll(vendor).then();
    }

    @PutMapping("/{id}")
    public Mono<Vendor> updateVendor(@RequestBody Vendor vendor, @PathVariable String id) {
        vendor.setId(id);
        return vendorRepository.save(vendor);
    }
    @PatchMapping("/{id}")
    Mono<Vendor> patchVendor(@RequestBody Vendor vendorToSave, @PathVariable String id){

        //*************************//
        //this belongs to service layer, which is here omitted for simplicity
        Vendor foundVendor= vendorRepository.findById(id).block();

        if(vendorToSave.getFirstName()!=null && !foundVendor.getFirstName().equalsIgnoreCase(vendorToSave.getFirstName())){
            foundVendor.setFirstName(vendorToSave.getFirstName());
            vendorRepository.save(foundVendor);
        }
        if(vendorToSave.getLastName()!=null && !foundVendor.getLastName().equalsIgnoreCase(vendorToSave.getLastName())){
            foundVendor.setLastName(vendorToSave.getLastName());
            vendorRepository.save(foundVendor);
        }
        return Mono.just(foundVendor);
        //************************//
    }
}