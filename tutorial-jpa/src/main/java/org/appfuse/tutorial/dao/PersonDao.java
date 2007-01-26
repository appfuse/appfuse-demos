package org.appfuse.tutorial.dao;

import org.appfuse.dao.GenericDao;
import org.appfuse.tutorial.model.Person;

import java.util.List;

/**
 * @author mraible
 */
public interface PersonDao<T, PK extends java.io.Serializable> extends GenericDao {
    public List<Person> findByLastName(String lastName);
}
