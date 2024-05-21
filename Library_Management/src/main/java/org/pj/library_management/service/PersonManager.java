package org.pj.library_management.service;

import org.pj.library_management.dao.entities.Person;

public interface PersonManager {

    public Person addPerson(Person person);
    public Person updatePerson(Person person);
    public Boolean deletePerson(Person person);
    public Person loginPerson(String username, String password);

}
