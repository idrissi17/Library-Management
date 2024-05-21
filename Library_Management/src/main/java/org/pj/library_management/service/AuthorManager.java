package org.pj.library_management.service;


import jakarta.transaction.Transactional;
import org.pj.library_management.dao.entities.Author;
import org.pj.library_management.dao.entities.Book;
import org.pj.library_management.dao.repository.AuthorRepository;
import org.springframework.data.domain.Page;

import java.util.List;

@Transactional
public interface AuthorManager {
    public Author addAuthor(Author author);
    public Author updateAuthor(Author author);
    public boolean deleteAuthor(Author author);
    public boolean deleteAuthorById(int id);

    public List<Author> getAllAuthors();
    public Page<Author> getAllAuthors(int page ,int pageSize);
    public Author getAuthorById(int id);
    public Page<Author>searchAuthors(String keyword,int page,int size);
    public List<Book> getAllBooksForAuthor(Author author);
    public Author getAuthorByFullName(String name);
    public Author findAuthorByName(String name);
}
