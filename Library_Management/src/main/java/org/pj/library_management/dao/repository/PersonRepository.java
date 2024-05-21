package org.pj.library_management.dao.repository;

import org.pj.library_management.dao.entities.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories
public interface PersonRepository extends JpaRepository<Person,Integer> {
    Person findByUsername(String username);


}
