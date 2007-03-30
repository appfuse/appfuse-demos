package org.appfuse.tutorial.dao.ibatis;

import org.appfuse.tutorial.dao.PersonDao;
import org.appfuse.tutorial.model.Person;
import org.appfuse.dao.ibatis.GenericDaoiBatis;

import java.util.List;

public class PersonDaoiBatis extends GenericDaoiBatis<Person, Long> implements PersonDao {

    public PersonDaoiBatis() {
        super(Person.class);
    }

    @SuppressWarnings("unchecked") 
    public List<Person> findByLastName(String lastName) {
        return  (List<Person>) getSqlMapClientTemplate().queryForList("findByLastName", lastName);
    }
}