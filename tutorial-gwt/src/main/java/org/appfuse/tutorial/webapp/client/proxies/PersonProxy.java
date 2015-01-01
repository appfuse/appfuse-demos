package org.appfuse.tutorial.webapp.client.proxies;

import org.appfuse.tutorial.model.Person;
import org.appfuse.tutorial.webapp.server.locators.PersonLocator;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = Person.class, locator = PersonLocator.class)
public interface PersonProxy extends EntityProxy {

    Long getId();

    void setId(Long id);

    Integer getVersion();

    void setVersion(Integer version);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);
}