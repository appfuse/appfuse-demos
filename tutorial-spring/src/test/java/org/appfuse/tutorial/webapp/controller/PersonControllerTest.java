package org.appfuse.tutorial.webapp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.appfuse.Constants;
import org.appfuse.webapp.controller.BaseControllerTestCase;
import org.springframework.web.servlet.ModelAndView;

public class PersonControllerTest extends BaseControllerTestCase {
	private PersonController c;

	public void setPersonController(PersonController c) {
		this.c = c;
	}

    public void testHandleRequest() throws Exception {
        ModelAndView mav = c.handleRequest((HttpServletRequest) null,
                                           (HttpServletResponse) null);
        Map m = mav.getModel();
        assertNotNull(m.get("personList"));
        assertEquals(mav.getViewName(), "personList");
    }
}