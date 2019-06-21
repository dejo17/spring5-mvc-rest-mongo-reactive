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
        Vendor pero = new Vendor();
        pero.setLastName("Peric");
        pero.setFirstName("Pero");

        Vendor dino = new Vendor();
        dino.setLastName("Dinic");
        dino.setFirstName("Dino");

        Vendor alenko = new Vendor();
        alenko.setLastName("Haramija");
        alenko.setFirstName("Alenko");

        Vendor dani = new Vendor();
        dani.setLastName("Glavic");
        dani.setFirstName("Dani");

        Vendor djuro = new Vendor();
        djuro.setLastName("Djuric");
        djuro.setFirstName("Djuro");

        vendorRepository.save(pero);
        vendorRepository.save(dino);
        vendorRepository.save(alenko);
        vendorRepository.save(dani);
        vendorRepository.save(djuro);

        System.out.println("Customers Loaded");
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setDescription("Fruits");

        Category dried = new Category();
        dried.setDescription("Dried");

        Category fresh = new Category();
        fresh.setDescription("Fresh");

        Category exotic = new Category();
        exotic.setDescription("Exotic");

        Category nuts = new Category();
        nuts.setDescription("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);


        System.out.println("Categories Loaded");
    }
}
