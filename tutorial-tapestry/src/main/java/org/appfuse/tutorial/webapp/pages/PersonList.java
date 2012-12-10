package org.appfuse.tutorial.webapp.pages;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.appfuse.service.GenericManager;
import org.appfuse.tutorial.model.Person;
import org.slf4j.Logger;

import java.util.List;

public class PersonList {
    @Inject
    private Logger log;

    @Inject
    @Service("personManager")
    private GenericManager<Person, Long> personManager;

    @Property
    private Person person;

    public List<Person> getPersons() {
        return personManager.getAll();
    }

    Object onDone() {
        return Home.class;
    }

    @InjectPage
    private PersonForm form;

    Object onAdd() {
        form.setPerson(new Person());
        return form;
    }

    Object onActionFromEdit(Long id) {
        log.debug("fetching person with id: {}", id);
        person =  personManager.get(id);
        form.setPerson(person);
        return form;
    }

    Object onSubmit() {
        return this;
    }
}
