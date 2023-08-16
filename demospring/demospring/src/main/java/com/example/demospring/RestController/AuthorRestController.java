package com.example.demospring.RestController;

import com.example.demospring.model.Author;
import com.example.demospring.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorRestController {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorRestController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @GetMapping("/get")
    public List<Author> getAllAuthors() {
        return (List<Author>) authorRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author != null) {
            return ResponseEntity.ok(author);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/post")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author, HttpServletRequest request) {
        String apiKey = request.getHeader("X-API-Key");
        if ("shabarish".equals(apiKey)) {
            Author newAuthor = authorRepository.save(author);
            return ResponseEntity.status(HttpStatus.CREATED).body(newAuthor);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author updatedAuthor, HttpServletRequest request) {
        String apiKey = request.getHeader("X-API-Key");
        if ("shabarish".equals(apiKey)) {
            Author author = authorRepository.findById(id).orElse(null);
            if (author != null) {
                author.setName(updatedAuthor.getName());
                Author savedAuthor = authorRepository.save(author);
                return ResponseEntity.ok(savedAuthor);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id, HttpServletRequest request) {
        String apiKey = request.getHeader("X-API-Key");
        if ("shabarish".equals(apiKey)) {
            Author author = authorRepository.findById(id).orElse(null);
            if (author != null) {
                authorRepository.delete(author);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}