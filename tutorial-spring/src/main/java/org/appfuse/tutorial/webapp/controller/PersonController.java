package org.appfuse.tutorial.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.appfuse.service.GenericManager;
import org.appfuse.tutorial.model.Person;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PersonController implements Controller {
    private final Log log = LogFactory.getLog(PersonController.class);
    private GenericManager<Person, Long> personManager = null;

    public void setPersonManager(GenericManager<Person, Long> personManager) {
        this.personManager = personManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        log.debug("entering 'handleRequest' method...");

        return new ModelAndView().addObject(personManager.getAll());
    }
}
