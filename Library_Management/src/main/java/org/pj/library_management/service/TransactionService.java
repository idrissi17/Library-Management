package org.pj.library_management.service;

import jakarta.transaction.Transactional;
import org.pj.library_management.dao.entities.Transaction;
import org.pj.library_management.dao.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class TransactionService implements TransactionManager{


    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public Transaction addTransaction(Transaction transaction) {
        try{
            return transactionRepository.save(transaction);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        try{
            return transactionRepository.save(transaction);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteTransaction(Transaction transaction) {
        try{
            transactionRepository.delete(transaction);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Transaction getTransactionById(int id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }
}
