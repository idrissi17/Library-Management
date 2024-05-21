package org.pj.library_management.service;

import jakarta.transaction.Transactional;
import org.pj.library_management.dao.entities.Author;
import org.pj.library_management.dao.entities.Book;
import org.pj.library_management.dao.entities.Transaction;
import org.pj.library_management.dao.repository.AuthorRepository;
import org.pj.library_management.dao.repository.BookRepository;
import org.pj.library_management.dao.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BookService implements BookManager{

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AuthorRepository   authorRepository;
    @Override
    public Book addBook(Book book) {
        try {
            return bookRepository.save(book);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public Book updateBook(Book book) {
        try {
            return bookRepository.save(book);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteBook(Book book) {
        try {
            bookRepository.delete(book);
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteAuthorById(int id) {
        try {
            bookRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public List<Book> getAllBooks(int page ,int size) {
        Pageable pageable=PageRequest.of(page,size);
        Page<Book>bookPage=bookRepository.findAll(pageable);
        return bookPage.getContent();
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Author getAuthorForThisBook(Book book) {
        return book.getAuthor();
    }

    @Override
    public List<Transaction> getAllTransactionsForBook(Book book) {
        return transactionRepository.findByBook(book);
    }

    @Override
    public Page<Book> searchBooks(String keyword, int page, int size) {
        return bookRepository.findByTitleContains(keyword,PageRequest.of(page,size));
    }

    @Override
    public List<Book> getBookByKeyword(String keyword) {
        return null;
    }

    @Override
    public Book getBookByTitle(String title) {
        try {
         return bookRepository.findByTitle(title);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
