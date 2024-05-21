package org.pj.library_management.service;

import jakarta.transaction.Transactional;
import org.pj.library_management.dao.entities.Author;
import org.pj.library_management.dao.entities.Book;
import org.pj.library_management.dao.entities.Transaction;
import org.springframework.data.domain.Page;

import java.util.List;

@Transactional
public interface BookManager {
    public Book addBook(Book book);
    public Book updateBook(Book book);
    public boolean deleteBook(Book book);
    public boolean deleteAuthorById(int id);
    public List<Book>getAllBooks(int page,int size);
    public List<Book>getAllBooks();
    public Book getBookById(int id);
    public Author getAuthorForThisBook(Book book);
    public List<Transaction> getAllTransactionsForBook(Book book);
    public Page<Book>searchBooks(String keyword,int page,int taille);
    public List<Book> getBookByKeyword(String keyword);
    public Book getBookByTitle(String title);

}
