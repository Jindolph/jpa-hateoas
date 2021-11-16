package com.jindolph.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
public class PersonController {
    private final PersonRepository repository;
    private final PersonModelAssembler assembler;

    public PersonController(PersonRepository repository, PersonModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping
    CollectionModel<EntityModel<Person>> all() {
        List<EntityModel<Person>> people = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(people, linkTo(methodOn(PersonController.class).all()).withSelfRel());
    }

    @PostMapping
    ResponseEntity<?> create(@RequestBody Person newPerson) {
        EntityModel<Person> entityModel = assembler.toModel(repository.save(newPerson));

        return ResponseEntity//
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
                .body(entityModel);
    }

    @GetMapping("/{id}")
    EntityModel<Person> one(@PathVariable Long id) {
        Person person = repository.findById(id) //
                .orElseThrow(() -> new PersonNotFoundException(id));
        return assembler.toModel(person);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> update(@RequestBody Person newPerson, @PathVariable Long id) {
        Person targetPerson = repository.findById(id) //
                .map(person -> {
                    person.setName(newPerson.getName());
                    person.setRole(newPerson.getRole());
                    return repository.save(person);
                }) //
                .orElseGet(() -> {
                    newPerson.setId(id);
                    return repository.save(newPerson);
                });

        EntityModel<Person> entityModel = assembler.toModel(targetPerson);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new PersonNotFoundException(id);
        }
    }
}
