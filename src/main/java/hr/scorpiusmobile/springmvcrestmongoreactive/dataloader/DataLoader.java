package hr.scorpiusmobile.springmvcrestmongoreactive.dataloader;

import hr.scorpiusmobile.springmvcrestmongoreactive.domain.Category;
import hr.scorpiusmobile.springmvcrestmongoreactive.domain.Vendor;
import hr.scorpiusmobile.springmvcrestmongoreactive.repositories.CategoryRepository;
import hr.scorpiusmobile.springmvcrestmongoreactive.repositories.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public DataLoader(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //we will load data only if repositories are empty
        if (categoryRepository.count().block() == 0) {
            loadCategories();
        }
        if (vendorRepository.count().block() == 0) {
            loadVendors();
        }
    }

    private void loadVendors() {
        Vendor pero = Vendor.builder().lastName("Peric").firstName("Pero").build();
        Vendor dino =  Vendor.builder().lastName("Dinic").firstName("Dino").build();
        Vendor alenko =  Vendor.builder().lastName("Haramija").firstName("Alenko").build();
        Vendor dani = Vendor.builder().lastName("Glavic").firstName("Dani").build();
        Vendor djuro =Vendor.builder().lastName("Djuric").firstName("Djuro").build();

        vendorRepository.save(pero).block();
        vendorRepository.save(dino).block();
        vendorRepository.save(alenko).block();
        vendorRepository.save(dani).block();
        vendorRepository.save(djuro).block();

        System.out.println("Customers loaded. "+vendorRepository.count().block() + " new vendors added." );
    }

    private void loadCategories() {
        Category fruits = Category.builder().description("Fruits").build();
        Category dried = Category.builder().description("Dried").build();
        Category fresh = Category.builder().description("Fresh").build();
        Category exotic = Category.builder().description("Exotic").build();
        Category nuts = Category.builder().description("Nuts").build();

        categoryRepository.save(fruits).block();
        categoryRepository.save(dried).block();
        categoryRepository.save(fresh).block();
        categoryRepository.save(exotic).block();
        categoryRepository.save(nuts).block();


        System.out.println("Categories loaded. "+categoryRepository.count().block() + " new categories added." );
    }
}
