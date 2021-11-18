package com.jindolph.rest;

import com.jindolph.rest.Custom.Article;
import com.jindolph.rest.Custom.ArticleRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PersonRepository personRepository, OrderRepository orderRepository,
            ArticleRepository articleRepository) {
        return args -> {
            for (int i = 0; i < 5; i++) {
                articleRepository.save(new Article("title" + i, "body" + i, "author" + i));
            }
            
            personRepository.save(new Person("Bilbo Baggins", "burglar"));
            personRepository.save(new Person("Frodo Baggins", "thief"));

            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));
            
            articleRepository.findAll().forEach(article -> log.info("Preloaded " + article));
            
            personRepository.findAll().forEach(person -> log.info("Preloaded " + person));
            orderRepository.findAll().forEach(order -> log.info("Preloaded " + order));
        };
    }
}