package org.appfuse.tutorial.webapp.controller;

import org.appfuse.tutorial.model.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import static org.junit.Assert.*;

public class PersonFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private PersonFormController form;
    private Person person;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/personform");
        request.addParameter("id", "1");

        person = form.showForm(request);
        assertNotNull(person);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/personform");
        request.addParameter("id", "1");

        person = form.showForm(request);
        assertNotNull(person);

        request = newPost("/personform");

        person = form.showForm(request);
        // update required fields

        BindingResult errors = new DataBinder(person).getBindingResult();
        form.onSubmit(person, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/personform");
        request.addParameter("delete", "");
        person = new Person();
        person.setId(2L);

        BindingResult errors = new DataBinder(person).getBindingResult();
        form.onSubmit(person, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
