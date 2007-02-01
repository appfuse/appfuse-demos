package org.appfuse.tutorial.webapp.controller;

import org.appfuse.webapp.controller.BaseControllerTestCase;
import org.appfuse.tutorial.model.Person;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

public class PersonFormControllerTest extends BaseControllerTestCase {
    private PersonFormController c;

    public void setPersonFormController(PersonFormController c) {
        this.c = c;
    }

    public void testEdit() throws Exception {
        log.debug("testing edit...");
        MockHttpServletRequest request = newGet("/personform.html");
        request.addParameter("id", "1");

        ModelAndView mv = c.handleRequest(request, new MockHttpServletResponse());

        Person person = (Person) mv.getModel().get(c.getCommandName());
        assertNotNull(person);
    }

    public void testSave() throws Exception {
        MockHttpServletRequest request = newGet("/personform.html");
        request.addParameter("id", "1");

        ModelAndView mv = c.handleRequest(request, new MockHttpServletResponse());

        Person person = (Person) mv.getModel().get(c.getCommandName());
        assertNotNull(person);

        request = newPost("/personform.html");
        super.objectToRequestParameters(person, request);
        request.addParameter("lastName", "Updated Last Name");

        mv = c.handleRequest(request, new MockHttpServletResponse());

        Errors errors = (Errors) mv.getModel().get(BindException.MODEL_KEY_PREFIX + "person");
        assertNull(errors);
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    public void testRemove() throws Exception {
        MockHttpServletRequest request = newPost("/personform.html");
        request.addParameter("delete", "");
        request.addParameter("id", "2");

        c.handleRequest(request, new MockHttpServletResponse());
        
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}