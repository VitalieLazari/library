package com.example.library.service;

import com.example.library.domain.Book;
import com.example.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService  {

    private final BookRepository bookRepository;

    public Collection<Book> findAll(){
        log.info("Request all books");
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id){
        log.info("Request book with id=['{}'].", id);
        return bookRepository.findById(id);
    }

    public Book update(Book book){
        log.info("Update book ['{}'].", book);
        return bookRepository.save(book);
    }

    public void deleteById(Long id){
        log.info("Book with id=['{}'] is deleted.", id);
        bookRepository.deleteById(id);
    }

    public Book add(Book book) {
        log.info("Create book ['{}'].", book);
        return bookRepository.save(book);
    }
}
