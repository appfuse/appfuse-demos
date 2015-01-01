package org.appfuse.tutorial.webapp.server.locators;

import org.appfuse.tutorial.model.Person;
import org.appfuse.tutorial.service.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.web.bindery.requestfactory.shared.Locator;

@Component
public class PersonLocator extends Locator<Person, Long> {

    @Autowired
    private PersonManager personManager;

    public Person create(Class<? extends Person> clazz) {
        return new Person();
    }

    public Person find(Class<? extends Person> clazz, Long id) {
        return personManager.get(id);
    }

    public Class<Person> getDomainType() {
        return Person.class;
    }

    public Long getId(Person person) {
        return person.getId();
    }

    public Class<Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(Person person) {
        return person.getVersion();
    }

    @Override
    public boolean isLive(Person domainObject) {
        return true;
    }
}