package org.pj.library_management.service;

import jakarta.transaction.Transactional;
import org.pj.library_management.dao.entities.Author;
import org.pj.library_management.dao.entities.Book;
import org.pj.library_management.dao.repository.AuthorRepository;
import org.pj.library_management.dao.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class AuthorService implements AuthorManager{

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Override
    public Author addAuthor(Author author) {
        try {
            return authorRepository.save(author);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public Author updateAuthor(Author author) {
        try {
            return authorRepository.save(author);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteAuthor(Author author) {
        try {
            authorRepository.delete(author);
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteAuthorById(int id) {
        try {
            authorRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Page<Author> getAllAuthors(int page, int pageSize) {
        return authorRepository.findAll(PageRequest.of(page,pageSize));
    }

    @Override
    public Author getAuthorById(int id) {
       return authorRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Author> searchAuthors(String keyword, int page, int pageSize) {
        return authorRepository.findByFullNameContains(keyword, PageRequest.of(page,pageSize));
    }

    @Override
    public List<Book> getAllBooksForAuthor(Author author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public Author getAuthorByFullName(String name) {
        return authorRepository.findByFullName(name);
    }

    @Override
    public Author findAuthorByName(String name) {
        return authorRepository.findByFullName(name);
    }
}
