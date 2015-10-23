package org.appfuse.tutorial.service;

import org.appfuse.service.GenericManager;
import org.appfuse.tutorial.model.Person;

import javax.ws.rs.PathParam;
import java.util.List;

public interface PersonManager extends GenericManager<Person, Long> {

    List<Person> findByLastName(@PathParam("lastname") String lastName);

    List<Person> getPeople();
}