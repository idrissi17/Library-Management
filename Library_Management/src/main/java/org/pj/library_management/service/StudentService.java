package org.pj.library_management.service;

import jakarta.transaction.Transactional;
import org.pj.library_management.dao.entities.Author;
import org.pj.library_management.dao.entities.Student;
import org.pj.library_management.dao.entities.Transaction;
import org.pj.library_management.dao.repository.StudentRepository;
import org.pj.library_management.dao.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class StudentService implements StudentManager{

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public Student addStudent(Student student) {
        try {
            return studentRepository.save(student);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public Student updateStudent(Student student) {
        try {
            return studentRepository.save(student);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteStudent(Student student) {
        try{
            studentRepository.delete(student);
            return true;
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteStudentById(int id) {
        try{
            studentRepository.deleteById(id);
            return true;
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Page<Student> getAllStudents(int page, int pageSize) {
        return studentRepository.findAll(PageRequest.of(page, pageSize));
    }

    @Override
    public List<Transaction> getAllTransactionsForStudent(Student student) {
        return transactionRepository.findByStudent(student);
    }

    @Override
    public Page<Student> searchStudent(String keyword, int page, int pageSize) {
        return studentRepository.findByFullNameContains(keyword,PageRequest.of(page,pageSize));
    }

    @Override
    public Student loginStudent(String email, String password) {

        Student student=studentRepository.findByEmail(email);
        if (student != null && student.getPassword().equals(password)) {
            return student;
        } else {
            return null;
        }
    }

    @Override
    public Student getStudentByUsername(String username) {
       try {
           return studentRepository.findByUsername(username);
       }catch (Exception e){
           System.out.println(e.getMessage());
           return null;
       }
    }

    @Override
    public Student getStudentByFullName(String fullName) {
        try {
            return studentRepository.findByFullName(fullName);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
