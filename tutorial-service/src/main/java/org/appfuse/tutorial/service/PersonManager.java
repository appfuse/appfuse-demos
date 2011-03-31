package org.appfuse.tutorial.service;

import org.appfuse.service.GenericManager;
import org.appfuse.tutorial.model.Person;

import java.util.List;

public interface PersonManager extends GenericManager<Person, Long> {
    List<Person> findByLastName(String lastName);
}