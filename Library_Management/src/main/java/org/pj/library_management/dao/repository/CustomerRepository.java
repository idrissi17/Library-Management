package org.pj.library_management.dao.repository;

import org.pj.library_management.dao.entities.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByUsername(String username);


}
