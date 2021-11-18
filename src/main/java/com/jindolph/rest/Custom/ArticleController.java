package com.jindolph.rest.Custom;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("articles")
public class ArticleController {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleModelAssembler articleModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Article>> all() {
        List<EntityModel<Article>> articles = articleRepository.findAll().stream() //
                .map(articleModelAssembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(articles, //
                linkTo(methodOn(ArticleController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Article> one(@PathVariable Long id) {
        Article article = articleRepository.findById(id) //
                .orElseThrow(() -> new ArticleNotFoundException(id));

        return articleModelAssembler.toModel(article);
    }
}
