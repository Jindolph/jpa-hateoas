package com.jindolph.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class RootController {

	@GetMapping
	RepresentationModel<?> index() {
		RepresentationModel<?> rootModel = new RepresentationModel<>();
		rootModel.add(linkTo(methodOn(PersonController.class).all()).withRel("people"));
		rootModel.add(linkTo(methodOn(OrderController.class).all()).withRel("orders"));
		return rootModel;
	}
}