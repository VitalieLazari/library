package com.example.library.service;

import com.example.library.domain.Author;
import com.example.library.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Collection<Author> findAll(){
        log.info("Request all authors");
        return authorRepository.findAll();
    }

    public Optional<Author> findById(Long id){
        log.info("Request author with id=['{}'].", id);
        return authorRepository.findById(id);
    }

    public Author update(Author author){
        log.info("Update author ['{}'].", author);
        return authorRepository.save(author);
    }

    public void deleteById(Long id){
        log.info("Book with id=['{}'] is deleted.", id);
        authorRepository.deleteById(id);
    }

    public Author add(Author author) {
        log.info("Create publisher ['{}'].", author);
        return authorRepository.save(author);
    }
}
