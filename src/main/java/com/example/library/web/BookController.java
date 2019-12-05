package com.example.library.web;

import com.example.library.domain.Book;
import com.example.library.domain.Publisher;
import com.example.library.exceptions.NotFoundBookException;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

import static java.lang.String.format;

@Slf4j
@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    ResponseEntity<Collection<Book>> getAll(){
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Book> getById(@PathVariable Long id){
        return ResponseEntity.ok(bookService.findById(id)
            .orElseThrow(()-> new NotFoundBookException(format("Book with id=['%d'] is not found", id))));
    }

    @PostMapping
    ResponseEntity<Book> add( @RequestBody Book book){

        log.info("Create book {}", book);
        return ResponseEntity.ok(bookService.add(Book.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .totalPages(book.getTotalPages())
                .publisher(book.getPublisher())
                .publishedDate(book.getPublishedDate())
                .rating(book.getRating())
                .build()));
    }

    @PutMapping("{id}")
    ResponseEntity<Book> update(@PathVariable("id") Long id, @RequestBody Book book){
        Optional<Book> bFromDb = bookService.findById(id);
        bFromDb.get().setTitle(book.getTitle());
        bFromDb.get().setAuthor(book.getAuthor());
        bFromDb.get().setIsbn(book.getIsbn());
        bFromDb.get().setTotalPages(book.getTotalPages());
        bFromDb.get().setPublisher(book.getPublisher());
        bFromDb.get().setPublishedDate(book.getPublishedDate());
        bFromDb.get().setRating(book.getRating());

        log.info("Save book {}", bFromDb.get());
        return ResponseEntity.ok(bookService.update(bFromDb.get()));
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id){
        log.info("Book with id=[{}] was deleted successfully.",id);
        bookService.deleteById(id);
    }

}
