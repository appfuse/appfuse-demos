package org.appfuse.tutorial.service;

import org.appfuse.service.BaseManagerTestCase;
import org.appfuse.tutorial.model.Person;

/**
 * @author mraible
 */
public class PersonManagerTest extends BaseManagerTestCase {

    private PersonManager personManager;

    public void setPersonManager(PersonManager personManager) {
        this.personManager = personManager;
    }

    public void testGetUser() throws Exception {
        Person person = personManager.get(1L);
        assertNotNull(person);

        log.debug(person);
    }

    public void testSaveUser() throws Exception {
        Person person = personManager.get(1L);
        person.setFirstName("NewName");

        log.debug("saving user with updated firstName: " + person);

        //noinspection UnusedAssignment
        person = personManager.save(person);

        person = personManager.get(1L);
        assertEquals("NewName", person.getFirstName());
    }

    public void testAddAndRemoveUser() throws Exception {
        Person person = new Person();
        person.setFirstName("Person");
        person.setLastName("ManagerTest");
        
        person = personManager.save(person);
        assertEquals("Person", person.getFirstName());

        log.debug("removing person...");

        personManager.remove(person.getId());

        try {
            //noinspection UnusedAssignment
            person = personManager.get(person.getId());
            fail("Expected 'Exception' not thrown");
        } catch (Exception e) {
            log.debug(e);
            assertNotNull(e);
        }
    }

}
