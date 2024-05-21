package org.pj.library_management.dao.repository;

import org.pj.library_management.dao.entities.Book;
import org.pj.library_management.dao.entities.Student;
import org.pj.library_management.dao.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    List<Transaction> findByBook(Book book);
    List<Transaction> findByStudent(Student student);
}
