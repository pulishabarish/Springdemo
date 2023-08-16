package com.example.demospring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;


@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String publisher;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonIgnore
    private Author author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book()
    {

    }
    public Book(String title, String publisher, Author author)
    {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }


    public Book(Long id, String title, String publisher, Author author) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.author = author;
    }


}
