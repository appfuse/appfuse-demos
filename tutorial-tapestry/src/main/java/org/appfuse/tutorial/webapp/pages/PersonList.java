package org.appfuse.tutorial.webapp.pages;

import java.util.List;

import org.appfuse.service.GenericManager;
import org.appfuse.tutorial.model.Person;
import org.appfuse.webapp.page.BasePage;
import org.apache.tapestry.IRequestCycle;

public abstract class PersonList extends BasePage {
    public abstract GenericManager<Person, Long> getPersonManager();

    public List getPersons() {
        return getPersonManager().getAll();
    }
    
    public void edit(IRequestCycle cycle) {
        Object[] parameters = cycle.getListenerParameters();
        Long id = (Long) parameters[0];

        if (log.isDebugEnabled()) {
            log.debug("fetching person with id: " + id);
        }

        Person person = getPersonManager().get(id);

        PersonForm nextPage = (PersonForm) cycle.getPage("PersonForm");
        nextPage.setPerson(person);
        cycle.activate(nextPage);
    }
}