package org.pj.library_management.dao.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "transactions")
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transaction_id;
    private String transactionUID = UUID.randomUUID().toString();
    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Book book;

    public Transaction(Integer id, String transactionUID, TransactionStatus transactionStatus, LocalDate borrowDate,
                       LocalDate returnDate, Student student, Book book) {
        this.transaction_id = id;
        this.transactionUID = transactionUID != null ? transactionUID : UUID.randomUUID().toString();
        this.transactionStatus = transactionStatus;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.student = student;
        this.book = book;
    }


}
