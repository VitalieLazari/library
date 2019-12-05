package com.example.library.web;

import com.example.library.domain.Publisher;
import com.example.library.exceptions.NotFoundPublisherException;
import com.example.library.repository.PublisherRepository;
import com.example.library.service.PublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

import static java.lang.String.format;

@Slf4j
@RestController
@RequestMapping("publisher")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    private final PublisherRepository publisherRepository;

    @GetMapping
    ResponseEntity<Collection<Publisher>> getAll(){
        return ResponseEntity.ok(publisherService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Publisher> getById(@PathVariable Long id){
        return ResponseEntity.ok(publisherService.findById(id)
            .orElseThrow(()-> new NotFoundPublisherException(format("Publisher with id=[%d] is not found", id))));
    }

    @PostMapping
    ResponseEntity<Publisher> add( @RequestBody Publisher publisher){

        log.info("Create publisher {}", publisher);
        return ResponseEntity.ok(publisherService.add(Publisher.builder()
                .name(publisher.getName())
                .build()));
    }

    @PutMapping("{id}")
    ResponseEntity<Publisher> update(@PathVariable("id") Long id, @RequestBody Publisher publisher){
        Optional<Publisher> pFromDb = publisherService.findById(id);
        pFromDb.get().setName(publisher.getName());
        log.info("Save publisher {}", pFromDb.get());
        return ResponseEntity.ok(publisherService.update(pFromDb.get()));
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id){
        log.info("Publisher with id=[{}] was deleted successfully.",id);
        publisherService.deleteById(id);
    }

}
