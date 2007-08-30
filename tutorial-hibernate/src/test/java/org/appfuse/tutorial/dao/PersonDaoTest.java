// START SNIPPET: test
package org.appfuse.tutorial.dao;

import org.appfuse.dao.BaseDaoTestCase;
import org.appfuse.tutorial.model.Person;
import org.springframework.dao.DataAccessException;

import java.util.List;

public class PersonDaoTest extends BaseDaoTestCase {
    private PersonDao personDao = null;

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }
    // END SNIPPET: test
    // START SNIPPET: testFindPersonByLastName
    public void testFindPersonByLastName() throws Exception {
        List<Person> people = personDao.findByLastName("Raible");
        assertTrue(people.size() > 0);
    }
    // END SNIPPET: testFindPersonByLastName

    // START SNIPPET: testAddAndRemovePerson
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

        try {
            personDao.get(person.getId());
            fail("Person found in database");
        } catch (DataAccessException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            assertNotNull(dae);
        }
    }
    // END SNIPPET: testAddAndRemovePerson
}
