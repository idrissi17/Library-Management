package org.pj.library_management.service;

import jakarta.transaction.Transactional;
import org.pj.library_management.dao.entities.Author;
import org.pj.library_management.dao.entities.Student;
import org.pj.library_management.dao.entities.Transaction;
import org.springframework.data.domain.Page;

import java.util.List;
public interface StudentManager {
    public Student addStudent(Student student);
    public Student updateStudent(Student student);
    public boolean deleteStudent(Student student);
    public boolean deleteStudentById(int id);
    public Student getStudentById(int id);
    public List<Student>getAllStudents();
    public Page<Student>getAllStudents(int page,int pageSize);
    public List<Transaction>getAllTransactionsForStudent(Student student);
    public Page<Student> searchStudent(String keyword, int page, int pageSize);
     public Student loginStudent(String username,String password);
     public Student getStudentByUsername(String username);
     public Student getStudentByFullName(String fullName);
}
