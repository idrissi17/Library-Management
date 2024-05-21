package org.pj.library_management.dao.repository;

import org.pj.library_management.dao.entities.Author;
import org.pj.library_management.dao.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book>findByAuthor(Author author);
    Book findByTitle(String title);
    Page<Book>findByTitleContains(String keyword, Pageable pageable);
}
