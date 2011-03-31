package org.appfuse.tutorial.service.impl;

import org.appfuse.service.impl.GenericManagerImpl;
import org.appfuse.tutorial.dao.PersonDao;
import org.appfuse.tutorial.model.Person;
import org.appfuse.tutorial.service.PersonManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("personManager")
public class PersonManagerImpl extends GenericManagerImpl<Person, Long> implements PersonManager {
    PersonDao personDao;

    public PersonManagerImpl(PersonDao personDao) {
        super(personDao);
        this.personDao = personDao;
    }

    public List<Person> findByLastName(String lastName) {
        return personDao.findByLastName(lastName);
    }
}