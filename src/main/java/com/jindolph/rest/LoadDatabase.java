package com.jindolph.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PersonRepository personRepository, OrderRepository orderRepository) {
        return args -> {
            personRepository.save(new Person("Bilbo Baggins", "burglar"));
            personRepository.save(new Person("Frodo Baggins", "thief"));

            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));
            
            personRepository.findAll().forEach(person -> log.info("Preloaded " + person));
            orderRepository.findAll().forEach(order -> log.info("Preloaded " + order));
        };
    }
}