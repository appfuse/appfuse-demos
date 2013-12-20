package org.appfuse.tutorial.dao;

import org.appfuse.dao.BaseDaoTestCase;
import org.appfuse.tutorial.model.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

import static org.junit.Assert.*;

public class PersonDaoTest extends BaseDaoTestCase {
    @Autowired
    private PersonDao personDao;

    @Test
    public void testFindPersonByLastName() throws Exception {
        List<Person> people = personDao.findByLastName("Raible");
        assertTrue(people.size() > 0);
    }

    @Test(expected=DataAccessException.class)
    public void testAddAndRemovePerson() throws Exception {
        Person person = new Person();
        person.setFirstName("Country");
        person.setLastName("Bry");

        person = personDao.save(person);
        flush();

        person = personDao.get(person.getId());

        assertEquals("Country", person.getFirstName());
        assertNotNull(person.getId());

        log.debug("removing person...");

        personDao.remove(person.getId());
        flush();

        // should throw DataAccessException
        personDao.get(person.getId());
    }
}