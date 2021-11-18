package com.jindolph.rest.Custom;

public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(Long id) {
        super("Cannot find article " + id);
    }
}
