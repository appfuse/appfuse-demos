package org.appfuse.tutorial.webapp.pages;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.corelib.components.EventLink;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.appfuse.service.GenericManager;
import org.appfuse.tutorial.model.Person;
import org.slf4j.Logger;

import java.util.List;

public class PersonList extends BasePage {

    @Inject
    @Service("personManager")
    private GenericManager<Person, Long> personManager;

    @Property
    private Person person;

    @Component(parameters = {"event=add"})
    private EventLink addTop, addBottom;

    @Component(parameters = {"event=done"})
    private EventLink doneTop, doneBottom;

    public List<Person> getPersons() {
        return personManager.getAll();
    }

    @Inject
    private Logger log;

    @InjectPage
    private PersonForm form;

    Object onAdd() {
        form.setPerson(new Person());
        return form;
    }

    Object onActionFromEdit(Long id) {
        log.debug("fetching person with id: {}", id);
        return form;
    }

    Object onDone() {
        return MainMenu.class;
    }
}