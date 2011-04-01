package org.appfuse.tutorial.webapp.action;

import java.io.Serializable;
import java.util.List;

import org.appfuse.tutorial.model.Person;
import org.appfuse.service.GenericManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("request")
@Component("personList")
public class PersonList extends BasePage implements Serializable {
    private GenericManager<Person, Long> personManager;

    @Autowired
    public void setPersonManager(@Qualifier("personManager") GenericManager<Person, Long> personManager) {
        this.personManager = personManager;
    }

    public PersonList() {
        setSortColumn("id"); // sets the default sort column
    }

    public List getPersons() {
        return sort(personManager.getAll());
    }
} 