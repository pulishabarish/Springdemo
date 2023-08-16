package com.example.demospring.model;

import javax.persistence.*;




import java.util.ArrayList;
import java.util.List;

@Table(name = "authors")
@Entity
public class Author {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<Book> books = new ArrayList<>();


    public Author(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    Author() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
