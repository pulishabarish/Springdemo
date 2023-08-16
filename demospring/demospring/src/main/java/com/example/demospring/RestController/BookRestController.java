package com.example.demospring.RestController;

import com.example.demospring.model.Book;
import com.example.demospring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookRestController {

    private final BookRepository bookRepository;

    @Autowired
    public BookRestController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Get all books
    @GetMapping("/get")
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    // Get a single book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book, HttpServletRequest request) {
        String apiKey = request.getHeader("X-API-Key");
        if ("your-secret-api-key".equals(apiKey)) {
            Book newBook = bookRepository.save(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook, HttpServletRequest request) {
        String apiKey = request.getHeader("X-API-Key");
        if ("your-secret-api-key".equals(apiKey)) {
            Book book = bookRepository.findById(id).orElse(null);
            if (book != null) {
                book.setTitle(updatedBook.getTitle());
                book.setPublisher(updatedBook.getPublisher());
                book.setAuthor(updatedBook.getAuthor());
                Book savedBook = bookRepository.save(book);
                return ResponseEntity.ok(savedBook);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id, HttpServletRequest request) {
        String apiKey = request.getHeader("X-API-Key");
        if ("your-secret-api-key".equals(apiKey)) {
            Book book = bookRepository.findById(id).orElse(null);
            if (book != null) {
                bookRepository.delete(book);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}