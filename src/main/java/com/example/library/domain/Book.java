package com.example.library.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Long totalPages;
    private Double rating;
    private String isbn;
    private Date publishedDate;
    @ManyToOne
    @JoinColumn
    private Publisher publisher;
    @ManyToOne
    @JoinColumn
    private Author author;
}
