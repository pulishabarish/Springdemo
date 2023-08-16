package com.example.demospring.controller;

import com.example.demospring.repository.AuthorRepository;
import com.example.demospring.repository.BookRepository;
import com.example.demospring.model.Author;
import com.example.demospring.model.Book;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;


import java.util.Optional;

@Controller
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorController(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @QueryMapping
    Iterable<Author> authors() {
        return authorRepository.findAll();
    }

    @QueryMapping
    Optional<Author> authorById(@Argument Long id){
        return authorRepository.findById(id);
    }

    @MutationMapping
    Book addBook(@Argument BookInput book) {
        Author author = authorRepository.findById(book.authorId())
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));
        Book b = new Book(book.title(), book.publisher(), author);
        return bookRepository.save(b);
    }

    @MutationMapping
    Book updateBook(@Argument Long bookId, @Argument BookInput book) {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        existingBook.setTitle(book.title());
        existingBook.setPublisher(book.publisher());
        return bookRepository.save(existingBook);
    }

    @MutationMapping
    Boolean deleteBook(@Argument Long bookId) {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        bookRepository.delete(existingBook);
        return true;
    }
    @MutationMapping
    Author addAuthor(@Argument String name) {
        Author author = new Author(null, name);
        return authorRepository.save(author);
    }

    record BookInput(String title, String publisher, Long authorId) {}
}
