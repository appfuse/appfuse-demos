package org.appfuse.tutorial.webapp.action;

import java.io.Serializable;
import java.util.List;

import org.appfuse.webapp.action.BasePage;
import org.appfuse.service.GenericManager;

public class PersonList extends BasePage implements Serializable {
    private GenericManager personManager;

    public void setPersonManager(GenericManager manager) {
        this.personManager = manager;
    }

    public PersonList() {
        setSortColumn("id"); // sets the default sort column
    }

    public List getPersons() {
        return sort(personManager.getAll());
    }
} 

