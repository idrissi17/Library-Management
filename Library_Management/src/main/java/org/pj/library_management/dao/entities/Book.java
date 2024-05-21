package org.pj.library_management.dao.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name="books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer book_id;
    private String title;
    private Genre genre;
    private boolean available;
    private LocalDate publicationYear;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String imageFileName;
    @ManyToOne
    private Author author;
    @OneToMany(mappedBy = "book")
    private Collection<Transaction>transactions=new ArrayList<>();


}
