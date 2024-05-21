package org.pj.library_management.service;

import jakarta.transaction.Transactional;
import org.pj.library_management.dao.entities.Transaction;

import java.util.List;
public interface TransactionManager {

    public Transaction addTransaction(Transaction transaction);
    public Transaction updateTransaction(Transaction transaction);
    public boolean deleteTransaction(Transaction transaction);

    public Transaction getTransactionById(int id);

    public List<Transaction>getAllTransaction();




}
