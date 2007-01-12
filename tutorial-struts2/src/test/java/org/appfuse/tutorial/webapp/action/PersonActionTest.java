package org.appfuse.tutorial.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import org.appfuse.service.GenericManager;
import org.appfuse.tutorial.model.Person;
import org.appfuse.webapp.action.BaseActionTestCase;

public class PersonActionTest extends BaseActionTestCase {
    private PersonAction action;
    private String personId;

    public void setPersonAction(PersonAction action) {
        this.action = action;
    }

    @Override
    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();
        GenericManager<Person, Long> personManager =
                (GenericManager<Person, Long>) applicationContext.getBean("personManager");

        // add a test person to the database
        Person person = new Person();
        person.setFirstName("Jack");
        person.setLastName("Raible");
        personManager.save(person);
        personId = person.getId().toString();
    }

    public void testSearch() throws Exception {
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertTrue(action.getPersons().size() >= 1);
    }
}