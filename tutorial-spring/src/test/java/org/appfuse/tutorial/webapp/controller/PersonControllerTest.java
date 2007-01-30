package org.appfuse.tutorial.webapp.controller;

import org.appfuse.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class PersonControllerTest extends BaseControllerTestCase {
	private PersonController c;

	public void setPersonController(PersonController c) {
		this.c = c;
	}

    public void testHandleRequest() throws Exception {
        ModelAndView mav = c.handleRequest(null, null);
        ModelMap m = mav.getModelMap();
        assertNotNull(m.get("personList"));
        assertTrue(((List) m.get("personList")).size() > 0);
    }
}