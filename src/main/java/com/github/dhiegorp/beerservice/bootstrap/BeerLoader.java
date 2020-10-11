package com.github.dhiegorp.beerservice.bootstrap;

import com.github.dhiegorp.beerservice.domain.Beer;
import com.github.dhiegorp.beerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository repository;

    public BeerLoader(BeerRepository repository) {
        this.repository = repository;
    }


    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if (repository.count() == 0) {
            repository.save(
                    Beer.builder()
                            .name("Some Beer #1")
                            .style("IPA")
                            .upc(1230000L)
                            .minOnHand(100)
                            .quantityToBrew(200)
                            .price(new BigDecimal("36.50"))
                            .build()
            );
            repository.save(
                    Beer.builder()
                            .name("Some Beer #2")
                            .style("IPA")
                            .upc(4230000L)
                            .minOnHand(50)
                            .quantityToBrew(150)
                            .price(new BigDecimal("100.50"))
                            .build()
            );


        }
    }
}
