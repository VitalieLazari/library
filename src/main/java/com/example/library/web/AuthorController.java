package com.example.library.web;

import com.example.library.domain.Author;
import com.example.library.exceptions.NotFoundAuthorException;
import com.example.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

import static java.lang.String.format;

@Slf4j
@RestController
@RequestMapping("author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    ResponseEntity<Collection<Author>> getAll(){
        return ResponseEntity.ok(authorService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Author> getById(@PathVariable Long id){
        return ResponseEntity.ok(authorService.findById(id)
            .orElseThrow(()-> new NotFoundAuthorException(format("Author with id=['%d'] is not found", id))));
    }

    @PostMapping
    ResponseEntity<Author> add(@RequestBody Author author){

        log.info("Create author {}", author);
        return ResponseEntity.ok(authorService.add(Author.builder()
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .build()));
    }

    @PutMapping("{id}")
    ResponseEntity<Author> update(@PathVariable("id") Long id, @RequestBody Author author){
        Optional<Author> aFromDb = authorService.findById(id);
        aFromDb.get().setFirstName(author.getFirstName());
        aFromDb.get().setLastName(author.getLastName());
        log.info("Save author {}", aFromDb.get());
        return ResponseEntity.ok(authorService.update(aFromDb.get()));
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id){
        log.info("Publisher with id=[{}] was deleted successfully.",id);
        authorService.deleteById(id);
    }

}
