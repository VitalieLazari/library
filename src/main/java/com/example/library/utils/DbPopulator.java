package com.example.library.utils;

import com.example.library.domain.Author;
import com.example.library.domain.Book;
import com.example.library.domain.Publisher;
import com.example.library.exceptions.NotFoundAuthorException;
import com.example.library.exceptions.NotFoundPublisherException;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class DbPopulator implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;

    @Override
    public void run(String... args) throws Exception {
        Stream.of(
                Publisher.builder()
                    .name("Pearson")
                    .build(),
                Publisher.builder()
                     .name("Reed Elsevier")
                     .build(),
                Publisher.builder()
                     .name("ThomsonReuters")
                     .build(),
                Publisher.builder()
                     .name("Wolters Kluwer")
                     .build(),
                Publisher.builder()
                     .name("Random House")
                     .build()

        ).forEach(publisher -> {
            log.info("Save publisher {}", publisher);
            publisherRepository.save(publisher);
        });


        Stream.of(
                Author.builder()
                    .firstName("Alison")
                    .lastName("Lohans")
                    .build(),
                Author.builder()
                    .firstName("George")
                    .lastName("Orwell")
                    .build(),
                Author.builder()
                    .firstName("John")
                    .lastName("Salmon")
                    .build(),
                Author.builder()
                        .firstName("John E.")
                        .lastName("Bennett")
                        .build(),
                Author.builder()
                        .firstName("Raphael")
                        .lastName("Dolin")
                        .build(),
                Author.builder()
                        .firstName("Mirko")
                        .lastName("Bagaric")
                        .build()
        ).forEach(author -> {
            log.info("Save author {}", author);
            authorRepository.save(author);
        });

        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        Stream.of(
                Book.builder()
                    .title("Nitty Gritty 2: This Land we Call Home")
                    .totalPages(189L)
                    .author(authorRepository.findById(1L)
                            .orElseThrow(() -> new NotFoundAuthorException("1l")))
                    .isbn("9781869705916")
                    .publisher(publisherRepository.findById(1L)
                            .orElseThrow(() -> new NotFoundPublisherException("1l")))
                    .publishedDate(dateformat.parse("30/11/2007"))
                    .rating(4.6)
                    .build(),
                Book.builder()
                        .title("Nineteen Eighty Four: York Notes Advanced")
                        .totalPages(112L)
                        .author(authorRepository.findById(2L)
                                .orElseThrow(() -> new NotFoundAuthorException("2l")))
                        .isbn("9781405807043")
                        .publisher(publisherRepository.findById(1L)
                                .orElseThrow(() -> new NotFoundPublisherException("1l")))
                        .publishedDate(dateformat.parse("18/08/2005"))
                        .rating(4.7)
                        .build(),
                Book.builder()
                        .title("Kanski's Clinical Ophthalmology, 9th Edition")
                        .totalPages(956L)
                        .author(authorRepository.findById(3L)
                                .orElseThrow(() -> new NotFoundAuthorException("3l")))
                        .isbn("9780702077111")
                        .publisher(publisherRepository.findById(2L)
                                .orElseThrow(() -> new NotFoundPublisherException("2l")))
                        .publishedDate(dateformat.parse("16/12/2019"))
                        .rating(4.5)
                        .build(),
                Book.builder()
                        .title("Mandell, Douglas, and Bennett's Principles and Practice of Infectious Diseases, 9th Edition")
                        .totalPages(4176L)
                        .author(authorRepository.findById(4L)
                                .orElseThrow(() -> new NotFoundAuthorException("4l")))
                        .isbn("9780323482554")
                        .publisher(publisherRepository.findById(2L)
                                .orElseThrow(() -> new NotFoundPublisherException("2l")))
                        .publishedDate(dateformat.parse("19/09/2019"))
                        .rating(4.8)
                        .build(),
                Book.builder()
                        .title("Sentencing in Australia")
                        .totalPages(2580L)
                        .author(authorRepository.findById(6L)
                                .orElseThrow(() -> new NotFoundAuthorException("6l")))
                        .isbn("9780455237053")
                        .publisher(publisherRepository.findById(3L)
                                .orElseThrow(() -> new NotFoundPublisherException("3l")))
                        .publishedDate(dateformat.parse("27/11/2015"))
                        .rating(4.9)
                        .build()

        ).forEach(book -> {
            log.info("Save book {}", book);
            bookRepository.save(book);
        });
    }
}
