package com.jindolph.rest;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(Long id) {
        super("Could not find " + id);
    }
}
