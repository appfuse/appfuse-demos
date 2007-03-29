package org.appfuse.tutorial.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.appfuse.dao.BaseDaoTestCase;
import org.appfuse.tutorial.model.Person;

public class PersonDaoTest extends BaseDaoTestCase {
    private PersonDao personDao = null;

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    public void testFindPersonByLastName() throws Exception {
        List<Person> people = personDao.findByLastName("Raible");
        assertTrue(people.size() > 0);
    }

    public void testAddAndRemovePerson() throws Exception {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Elway");

        person = personDao.save(person);

        assertEquals("John", person.getFirstName());
        assertNotNull(person.getId());

        log.debug("removing person...");

        personDao.remove(person.getId());

        try {
            personDao.get(person.getId());
            fail("Person found in database");
        } catch (EntityNotFoundException enf) {
            log.debug("Expected exception: " + enf.getMessage());
            assertNotNull(enf);
        }
    }
}
