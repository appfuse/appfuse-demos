package org.appfuse.tutorial.dao.ibatis;

import org.appfuse.dao.ibatis.GenericDaoiBatis;
import org.appfuse.tutorial.dao.PersonDao;
import org.appfuse.tutorial.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("personDao")
public class PersonDaoiBatis extends GenericDaoiBatis<Person, Long> implements PersonDao {

    public PersonDaoiBatis() {
        super(Person.class);
    }

    @SuppressWarnings("unchecked") 
    public List<Person> findByLastName(String lastName) {
        return  (List<Person>) getSqlMapClientTemplate().queryForList("findByLastName", lastName);
    }
}