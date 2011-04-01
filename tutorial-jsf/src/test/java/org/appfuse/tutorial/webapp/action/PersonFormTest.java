package org.appfuse.tutorial.webapp.action;

import org.appfuse.service.GenericManager;
import org.appfuse.tutorial.model.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.junit.Assert.*;

public class PersonFormTest extends BasePageTestCase {
    private PersonForm bean;
    @Autowired @Qualifier("personManager")
    private GenericManager<Person, Long> personManager;

    @Override
    @Before
    public void onSetUp() {
        super.onSetUp();
        bean = new PersonForm();
        bean.setPersonManager(personManager);
    }

    @Override
    @After
    public void onTearDown() {
        super.onTearDown();
        bean = null;
    }

    @Test
    public void testAdd() throws Exception {
        Person person = new Person();
        // set required fields
        person.setFirstName("firstName");
        person.setLastName("lastName");
        bean.setPerson(person);

        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        bean.setId(1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getPerson());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testSave() {
        bean.setId(1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getPerson());
        Person person = bean.getPerson();

        // update fields
        person.setFirstName("firstName");
        person.setLastName("lastName");
        bean.setPerson(person);

        assertEquals("edit", bean.save());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testRemove() throws Exception {
        Person person = new Person();
        person.setId(2L);
        bean.setPerson(person);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
} 