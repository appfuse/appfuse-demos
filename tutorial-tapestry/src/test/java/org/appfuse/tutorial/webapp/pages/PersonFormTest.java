package org.appfuse.tutorial.webapp.pages;

import org.apache.tapestry.engine.ILink;
import org.appfuse.tutorial.model.Person;
import org.appfuse.webapp.pages.MockRequestCycle;
import org.appfuse.webapp.pages.BasePageTestCase;
import org.appfuse.service.GenericManager;

import java.util.HashMap;
import java.util.Map;

public class PersonFormTest extends BasePageTestCase {
    private PersonForm page;

    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();
        // these can be mocked if you want a more "pure" unit test
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("personManager", applicationContext.getBean("personManager"));
        page = (PersonForm) getPage(PersonForm.class, map);
    }

    protected void onTearDownAfterTransaction() throws Exception {
        super.onTearDownAfterTransaction();
        page = null;
    }

    public void testAdd() throws Exception {
        Person person = new Person();
        // update the object's fields
        person.setFirstName("Abbie");
        person.setLastName("Loo");
        page.setPerson(person);

        ILink link = page.save(new MockRequestCycle(this.getClass().getPackage().getName()));
        assertNotNull(page.getPerson());
        assertFalse(page.hasErrors());
        assertEquals("PersonList" + EXTENSION, link.getURL());
    }

    public void testSave() {
        GenericManager<Person, Long> personManager =
                (GenericManager<Person, Long>) applicationContext.getBean("personManager");
        Person person = personManager.get(1L);

        // update fields
        person.setFirstName("Jack");
        person.setLastName("Jack");
        page.setPerson(person);

        ILink link = page.save(new MockRequestCycle(this.getClass().getPackage().getName()));
        assertNotNull(page.getPerson());
        assertFalse(page.hasErrors());
        assertNull(link);
    }

    public void testRemove() throws Exception {
        Person person = new Person();
        person.setId(2L);
        page.setPerson(person);
        page.delete(new MockRequestCycle(this.getClass().getPackage().getName()));
        assertFalse(page.hasErrors());
    }
}
