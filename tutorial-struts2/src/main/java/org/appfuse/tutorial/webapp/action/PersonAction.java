package org.appfuse.tutorial.webapp.action;

import org.appfuse.webapp.action.BaseAction;
import org.appfuse.tutorial.model.Person;
import org.appfuse.service.GenericManager;

import java.util.List;

public class PersonAction extends BaseAction {
    private GenericManager<Person, Long> personManager;
    private List persons;

    public void setPersonManager(GenericManager<Person, Long> personManager) {
        this.personManager = personManager;
    }

    public List getPersons() {
        return persons;
    }

    public String list() {
        persons = personManager.getAll();
        return SUCCESS;
    }
}