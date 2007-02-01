package org.appfuse.tutorial.webapp.pages;

import java.util.HashMap;
import java.util.Map;

import org.appfuse.webapp.pages.BasePageTestCase;
import org.appfuse.webapp.pages.MockRequestCycle;
import org.apache.tapestry.engine.RequestCycle;

public class PersonListTest extends BasePageTestCase {
    private PersonList page;

    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();
        // these can be mocked if you want a more "pure" unit test
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("personManager", applicationContext.getBean("personManager"));
        page = (PersonList) getPage(PersonList.class, map);
    }

    protected void onTearDownAfterTransaction() throws Exception {
        super.onTearDownAfterTransaction();
        page = null;
    }

    public void testSearch() throws Exception {
        assertTrue(page.getPersons().size() >= 1);
    }

    public void testEdit() throws Exception {
        RequestCycle cycle = new MockRequestCycle(this.getClass().getPackage().getName());
        cycle.setServiceParameters(new Object[] {1L});
        page.edit(cycle);
        assertFalse(page.hasErrors());
    } 
}