package org.appfuse.tutorial.webapp.action;

import org.appfuse.tutorial.model.Person;
import org.appfuse.webapp.action.BasePageTestCase;
import org.appfuse.service.GenericManager;

public class PersonFormTest extends BasePageTestCase {
    private PersonForm bean;
    private GenericManager<Person, Long> personManager;

    public void setPersonManager(GenericManager<Person, Long> personManager) {
        this.personManager = personManager;
    }

    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new PersonForm();
        bean.setPersonManager(personManager);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
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

    /*
    public void testResourceBundle() {
        ResourceBundle bundle = ResourceBundle.getBundle(Constants.BUNDLE_KEY, Locale.ENGLISH, this.getClass().getClassLoader());
        log.debug("bundle: " + bundle.getString("person.added"));

        assertNotNull(bean.getText("person.added"));
        log.debug("bean.added: " + bean.getText("person.added"));
    }*/

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