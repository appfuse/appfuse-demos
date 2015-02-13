package org.appfuse.tutorial.webapp.action;

import org.appfuse.service.GenericManager;
import org.appfuse.tutorial.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("personList")
@Scope("request")
public class PersonList extends BasePage implements Serializable {
    private GenericManager<Person, Long> personManager;

    @Autowired
    public void setPersonManager(@Qualifier("personManager") GenericManager<Person, Long> personManager) {
        this.personManager = personManager;
    }

    public PersonList() {
        setSortColumn("id"); // sets the default sort column
    }

    public List<Person> getPersons() {
        return sort(personManager.getAll());
    }

    public String search() {
        return "success";
    }
}