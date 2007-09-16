package org.appfuse.tutorial.webapp.action;

import org.appfuse.webapp.action.BasePageTestCase;
import org.appfuse.service.GenericManager;
import org.appfuse.tutorial.model.Person;

public class PersonListTest extends BasePageTestCase {
    private PersonList bean;
    private GenericManager<Person, Long> personManager;

    public void setPersonManager(GenericManager<Person, Long> personManager) {
        this.personManager = personManager;
    }

    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() throws Exception {
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
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

    public void testSearch() throws Exception {
        assertTrue(bean.getPersons().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}