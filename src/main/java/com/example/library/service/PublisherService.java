package com.example.library.service;

import com.example.library.domain.Publisher;
import com.example.library.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public Collection<Publisher> findAll(){
        log.info("Request all publishers");
        return publisherRepository.findAll();
    }

    public Optional<Publisher> findById(Long id){
        log.info("Request publisher with id=['{}'].", id);
        return publisherRepository.findById(id);
    }

    public Publisher update(Publisher publisher){
        log.info("Update publisher ['{}'].", publisher);
        return publisherRepository.save(publisher);
    }

    public void deleteById(Long id){
        log.info("Publisher with id=['{}'] is deleted.", id);
        publisherRepository.deleteById(id);
    }

    public Publisher add(Publisher publisher) {
        log.info("Create publisher ['{}'].", publisher);
        return publisherRepository.save(publisher);
    }
}
