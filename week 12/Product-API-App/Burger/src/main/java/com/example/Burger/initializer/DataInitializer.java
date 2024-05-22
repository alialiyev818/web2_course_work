package com.example.Burger.initializer;

import com.example.Burger.model.entity.Burger;
import com.example.Burger.repository.BurgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BurgerRepository burgerRepository;

    @Autowired
    public DataInitializer(BurgerRepository burgerRepository) {
        this.burgerRepository = burgerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Burger burger1 = new Burger();
        burger1.setName("Big Mac");
        burger1.setLocation("Mcdonalds");

        Burger burger2 = new Burger();
        burger2.setName("Twister");
        burger2.setLocation("KFC");

        Burger burger3 = new Burger();
        burger3.setName("Whopper");
        burger3.setLocation("Burger King");

        burgerRepository.save(burger1);
        burgerRepository.save(burger2);
        burgerRepository.save(burger3);
    }
}
