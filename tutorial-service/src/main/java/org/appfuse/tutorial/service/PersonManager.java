package org.appfuse.tutorial.service;

import org.appfuse.service.GenericManager;
import org.appfuse.tutorial.model.Person;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface PersonManager extends GenericManager<Person, Long> {
    public List<Person> findByLastName(String lastName);
}