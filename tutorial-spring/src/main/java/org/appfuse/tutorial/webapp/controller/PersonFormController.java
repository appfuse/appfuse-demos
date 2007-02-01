package org.appfuse.tutorial.webapp.controller;

import org.apache.commons.lang.StringUtils;
import org.appfuse.service.GenericManager;
import org.appfuse.tutorial.model.Person;
import org.appfuse.webapp.controller.BaseFormController;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class PersonFormController extends BaseFormController {
    private GenericManager<Person, Long> personManager = null;

    public void setPersonManager(GenericManager<Person, Long> personManager) {
        this.personManager = personManager;
    }

    public PersonFormController() {
        setCommandClass(Person.class);
        setCommandName("person");
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return personManager.get(new Long(id));
        }
        
        return new Person();
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        log.debug("entering 'onSubmit' method...");

        Person person = (Person) command;
        boolean isNew = (person.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();
        
        if (request.getParameter("delete") != null) {
            personManager.remove(person.getId());
            saveMessage(request, getText("person.deleted", locale));
        } else {
            personManager.save(person);
            String key = (isNew) ? "person.added" : "person.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:personform.html?id=" + person.getId();
            }
        }

        return new ModelAndView(success);
    }
}