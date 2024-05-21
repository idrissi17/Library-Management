package org.pj.library_management.dao.repository;

import org.pj.library_management.dao.entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {

    Author findByFullName(String fullName);
Page<Author>findByFullNameContains(String keyword, Pageable pageable);
}
