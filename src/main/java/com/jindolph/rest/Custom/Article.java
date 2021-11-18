package com.jindolph.rest.Custom;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Article {
    private @Id @GeneratedValue Long id;

    private String title;

    private @Lob String body;

    private String author;

    Article () {} //기본 생성자 접근제한 일관성 보장.

    // public 사용자를 쓰는 생성자보다는 Lombok의 Builder 사용이 권장이지만, 
    // 이 프로젝트에서는 Lombok을 쓰지 않음.
    public Article(String title, String body, String author) {
        this.title = title;
        this.body = body;
        this.author = author;
    }

    // setter도 일관성 보장을 위해서 사용 자제.

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", body='" + getBody() + "'" +
            ", author='" + getAuthor() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Article)) {
            return false;
        }
        Article article = (Article) o;
        return Objects.equals(id, article.id) && Objects.equals(title, article.title) && Objects.equals(body, article.body) && Objects.equals(author, article.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, body, author);
    }
    
}