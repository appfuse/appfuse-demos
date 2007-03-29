package org.appfuse.tutorial.dao.hibernate;

import org.appfuse.tutorial.model.Person;
import org.appfuse.tutorial.dao.PersonDao;
import org.appfuse.dao.hibernate.GenericDaoHibernate;

import java.util.List;

public class PersonDaoHibernate extends GenericDaoHibernate<Person, Long> implements PersonDao {

    public PersonDaoHibernate() {
        super(Person.class);
    }

    public List<Person> findByLastName(String lastName) {
        return getHibernateTemplate().find("from Person where lastName=?", lastName);
    }
}
