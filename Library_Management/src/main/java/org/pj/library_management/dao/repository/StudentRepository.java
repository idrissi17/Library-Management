package org.pj.library_management.dao.repository;

import org.pj.library_management.dao.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    Student findByUsername(String username);
    Student findByFullName(String fullName);
    Page<Student>findByFullNameContains(String keyword, Pageable pageable);
    Student findByEmail(String email);
}
