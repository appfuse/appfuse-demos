package org.appfuse.tutorial.webapp.pages;

import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.engine.ILink;
import org.apache.tapestry.event.PageBeginRenderListener;
import org.apache.tapestry.event.PageEvent;

import org.appfuse.service.GenericManager;
import org.appfuse.tutorial.model.Person;
import org.appfuse.webapp.pages.BasePage;

public abstract class PersonForm extends BasePage implements PageBeginRenderListener {
    public abstract GenericManager<Person, Long> getPersonManager();
    public abstract void setPerson(Person person);
    public abstract Person getPerson();

    public void pageBeginRender(PageEvent event) {
        if (getPerson() == null) {
            setPerson(new Person());
        }
    }

    public ILink cancel(IRequestCycle cycle) {
        log.debug("Entering 'cancel' method");
        return getEngineService().getLink(false, "PersonList");
    }

    public ILink delete(IRequestCycle cycle) {
        log.debug("entered 'delete' method");

        getPersonManager().remove(getPerson().getId());

        PersonList nextPage = (PersonList) cycle.getPage("PersonList");
        nextPage.setMessage(getText("person.deleted"));
        return getEngineService().getLink(false, nextPage.getPageName());
    }

    public ILink save(IRequestCycle cycle) {
        if (getDelegate().getHasErrors()) {
            return null;
        }

        boolean isNew = (getPerson().getId() == null);

        getPersonManager().save(getPerson());

        String key = (isNew) ? "person.added" : "person.updated";

        if (isNew) {
            PersonList nextPage = (PersonList) cycle.getPage("PersonList");
            nextPage.setMessage(getText(key));
            return getEngineService().getLink(false, nextPage.getPageName());
        } else {
            setMessage(getText(key));
            return null; // return to current page
        }
    }
}
