package org.pj.library_management.dao.repository;

import org.pj.library_management.dao.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Optional<Admin>findByUsername(String username);

    Admin findByUsernameOrPassword(String userName, String password);
}
