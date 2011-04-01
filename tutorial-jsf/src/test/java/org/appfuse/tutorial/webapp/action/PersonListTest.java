package org.appfuse.tutorial.webapp.action;

import org.appfuse.service.GenericManager;
import org.appfuse.tutorial.model.Person;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.junit.Assert.*;

public class PersonListTest extends BasePageTestCase {
    private PersonList bean;
    @Autowired @Qualifier("personManager")
    private GenericManager<Person, Long> personManager;

    @Override
    @Before
    @SuppressWarnings("unchecked")
    public void onSetUp() {
        super.onSetUp();
        bean = new PersonList();
        bean.setPersonManager(personManager);

        // add a test person to the database
        Person person = new Person();
        person.setFirstName("Abbie Loo");
        person.setLastName("Raible");
        personManager.save(person);
    }

    @Override
    @After
    public void onTearDown() {
        super.onTearDown();
        bean = null;
    }

    @Test
    public void testSearch() throws Exception {
        assertTrue(bean.getPersons().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}