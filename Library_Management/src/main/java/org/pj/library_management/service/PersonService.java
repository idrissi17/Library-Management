package org.pj.library_management.service;


import org.pj.library_management.dao.entities.Person;
import org.pj.library_management.dao.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements PersonManager {

    @Autowired
    private PersonRepository personRepository;
    @Override
    public Person addPerson(Person person) {
        return null;
    }

    @Override
    public Person updatePerson(Person person) {
        return null;
    }

    @Override
    public Boolean deletePerson(Person person) {
        return null;
    }

    @Override
    public Person loginPerson(String username, String password) {
        Person person = personRepository.findByUsername(username);
        if (person != null && person.getPassword().equals(password)){
            return person;
        }
        return null;
    }
}
