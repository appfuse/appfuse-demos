package org.appfuse.tutorial.webapp.action;

import org.appfuse.tutorial.model.Person;
import org.appfuse.webapp.action.BasePageTestCase;

public class PersonFormTest extends BasePageTestCase {
    private PersonForm bean;

    protected void setUp() throws Exception {
        super.setUp();
        bean = (PersonForm) getManagedBean("personForm");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        bean = null;
    }

    public void testAdd() throws Exception {
        Person person = new Person();
        // set required fields
        person.setFirstName("firstName");
        person.setLastName("lastName");
        bean.setPerson(person);

        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }

    public void testEdit() throws Exception {
        log.debug("testing edit...");
        bean.setId(1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getPerson());
        assertFalse(bean.hasErrors());
    }

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

    public void testRemove() throws Exception {
        Person person = new Person();
        person.setId(2L);
        bean.setPerson(person);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
} 