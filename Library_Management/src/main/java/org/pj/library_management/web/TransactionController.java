package org.pj.library_management.web;


import jakarta.validation.Valid;
import org.pj.library_management.dao.entities.*;
import org.pj.library_management.service.BookManager;
import org.pj.library_management.service.StudentManager;
import org.pj.library_management.service.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TransactionController {

   @Autowired
   private TransactionManager transactionManager;
   @Autowired
   private BookManager bookManager;
   @Autowired
   private StudentManager studentManager;

    @GetMapping("/user/transactionList")
    public String transactionsList(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Student student=studentManager.getStudentByUsername(username);
            model.addAttribute("student",student);
            List<Transaction> transactions = studentManager.getAllTransactionsForStudent(student);
            model.addAttribute("transactions", transactions);
        }



        return "TransactionList";
    }
    @GetMapping("/admin/transactionList")
    public String transactionsListAdmin(Model model){
        List<Transaction> transactions = transactionManager.getAllTransaction();
        model.addAttribute("transactions", transactions);
        return "TransactionListAdmin";
    }

    @GetMapping("/user/transactionGet")
    public String transactionGet(Model model, @RequestParam(name="id") Integer id){
        Book book =bookManager.getBookById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Student student=studentManager.getStudentByUsername(username);
            model.addAttribute("student",student);
        }
        model.addAttribute("book",book);
        model.addAttribute("status", TransactionStatus.values());
        return "TransactionBorrow";
    }
    @PostMapping("/user/transactionPost")
    public String transactionPost(Model model,
                                  @RequestParam(name = "title")String title,
                                  @RequestParam(name = "studentName")String studentName,
                                  @RequestParam(name = "status")TransactionStatus status,
                                  @RequestParam(name = "borrowDate")LocalDate borrowDate,
                                  @RequestParam(name = "returnDate")LocalDate returnDate){

        Book book =bookManager.getBookByTitle(title);
        Student student=studentManager.getStudentByFullName(studentName);
        Transaction tx = new Transaction();
        tx.setBook(book);
        tx.setStudent(student);
        tx.setTransactionStatus(status);
        tx.setBorrowDate(borrowDate);
        tx.setReturnDate(returnDate);
        transactionManager.addTransaction(tx);
        return "redirect:/user/transactionList";
    }
    @GetMapping("/admin/deleteTransaction")
    public String deleteAuthor(Model model,@RequestParam(name="id")Integer id ){
        Transaction transaction =transactionManager.getTransactionById(id);
        if(transactionManager.deleteTransaction(transaction)){
            return "redirect:/admin/transactionList";
        }else {
            return "error";
        }
    }
    @GetMapping("/admin/editTransaction")
    public String editTransaction(Model model,@RequestParam(name="id")Integer id){
        Transaction transaction =transactionManager.getTransactionById(id);
        model.addAttribute("transaction",transaction);
        model.addAttribute("status",TransactionStatus.values());
        return "EditTransaction";
    }
    @PostMapping("/admin/updateTransaction")
    public String updateTransaction(Model model,
                                    @RequestParam(name = "id")Integer id,
                                    @RequestParam(name = "studentName") String studentName,
                                    @RequestParam(name = "bookName") String bookName,
                                    @RequestParam(name = "status") TransactionStatus status,
                                    @RequestParam(name = "borrowDate") LocalDate borrowDate,
                                    @RequestParam(name = "returnDate") LocalDate returnDate){

        Transaction transaction =transactionManager.getTransactionById(id);
        transaction.setTransactionStatus(status);
        transaction.setBorrowDate(borrowDate);
        transaction.setReturnDate(returnDate);
        Student student = studentManager.getStudentByFullName(studentName);
        transaction.setStudent(student);
        Book book =bookManager.getBookByTitle(bookName);
        transaction.setBook(book);
        transactionManager.updateTransaction(transaction);
        bookManager.updateBook(book);
        studentManager.updateStudent(student);
        return "redirect:/admin/transactionList";
    }

    @GetMapping("/admin/addTransaction")
    public String addTransaction( Model model){
        List<Student> students =studentManager.getAllStudents();
        List<Book> books = bookManager.getAllBooks();
        model.addAttribute("addTransaction",new Transaction());
        model.addAttribute("students",students);
        model.addAttribute("status",TransactionStatus.values());
        model.addAttribute("books",books);
           return  "AddTransaction";
    }

    @PostMapping("/admin/addTransactionPost")
    public String addTransaction(Model model,@RequestParam(name="transactionUID") String transactionUID,
                                 @RequestParam(name="studentName") String studentName,
                                 @RequestParam(name="transactionStatus") TransactionStatus transactionStatus,
                                 @RequestParam(name="borrowDate") LocalDate borrowDate,
                                 @RequestParam(name="returnDate") LocalDate returnDate,
                                 @RequestParam(name="bookName") String bookName) {

        Book book =bookManager.getBookByTitle(bookName);
        Student student=studentManager.getStudentByFullName(studentName);
        Transaction transaction =new Transaction();
        transaction.setTransactionUID(transactionUID);
        transaction.setTransactionStatus(transactionStatus);
        transaction.setBorrowDate(borrowDate);
        transaction.setReturnDate(returnDate);
        transaction.setBook(book);
        transaction.setStudent(student);
        transactionManager.addTransaction(transaction);
        studentManager.updateStudent(student);
        bookManager.updateBook(book);
        return "redirect:/admin/transactionList";
    }
}
