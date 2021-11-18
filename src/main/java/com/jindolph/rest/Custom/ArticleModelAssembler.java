package com.jindolph.rest.Custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ArticleModelAssembler implements RepresentationModelAssembler<Article, EntityModel<Article>> {
    @Autowired
    ArticleRepository articleRepository;

    @Override
    public EntityModel<Article> toModel(Article article) {
        EntityModel<Article> articleModel = EntityModel.of(article,
                linkTo(methodOn(ArticleController.class).one(article.getId())).withRel(article.getTitle()));

        if (article.getId() == 1) {
            articleModel.add(linkTo(methodOn(ArticleController.class).one(article.getId() + 1)).withRel("after"));
        } else if (article.getId() >= articleRepository.count()) {
            articleModel.add(linkTo(methodOn(ArticleController.class).one(article.getId() - 1)).withRel("befre"));
        } else {
            articleModel.add(linkTo(methodOn(ArticleController.class).one(article.getId() - 1)).withRel("before"));
            articleModel.add(linkTo(methodOn(ArticleController.class).one(article.getId() + 1)).withRel("after"));
        }

        return articleModel;
    }
}
