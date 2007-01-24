package org.appfuse.tutorial.webapp.action;

import org.appfuse.webapp.action.BasePageTestCase;
import org.appfuse.service.GenericManager;
import org.appfuse.tutorial.model.Person;

public class PersonListTest extends BasePageTestCase {
    private PersonList bean;

    protected void setUp() throws Exception {
        super.setUp();
        bean = (PersonList) getManagedBean("personList");
        GenericManager<Person, Long> personManager =
                (GenericManager<Person, Long>) applicationContext.getBean("personManager");

        // add a test person to the database
        Person person = new Person();
        person.setFirstName("Abbie Loo");
        person.setLastName("Raible");
        personManager.save(person);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        bean = null;
    }

    public void testSearch() throws Exception {
        assertTrue(bean.getPersons().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}