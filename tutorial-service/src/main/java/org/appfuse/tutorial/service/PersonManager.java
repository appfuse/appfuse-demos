package org.appfuse.tutorial.service;

import org.appfuse.service.GenericManager;
import org.appfuse.tutorial.model.Person;

import java.util.List;

/**
 * @author mraible
 */
public interface PersonManager extends GenericManager<Person, Long> {
    public List<Person> findByLastName(String lastName);
}