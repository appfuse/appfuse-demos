package org.appfuse.tutorial.dao.jpa;

import org.appfuse.tutorial.model.Person;
import org.appfuse.tutorial.dao.PersonDao;
import org.appfuse.dao.jpa.GenericDaoJpa;

import javax.persistence.Query;
import java.util.List;

public class PersonDaoJpa extends GenericDaoJpa implements PersonDao {

    public PersonDaoJpa() {
        super(Person.class);
    }

    public List<Person> findByLastName(String lastName) {
        Query q = super.entityManager.createQuery("select p from org.appfuse.tutorial.model.Person p where p.lastName=?");
        q.setParameter(1, lastName);
        return q.getResultList();
    }
}
