package org.appfuse.tutorial.webapp.pages;

import org.appfuse.service.GenericManager;
import org.appfuse.tutorial.model.Person;

import org.apache.wicket.model.LoadableDetachableModel;

public class PersonModel extends LoadableDetachableModel {
    private GenericManager<Person, Long> personManager;
    private Long id;

    public PersonModel(GenericManager personManager) {
        this.personManager = personManager;
    }

    /**
    * @param person object this model will represent
    * @param personManager the personManager
    */
    public PersonModel(Person person, GenericManager personManager) {
        super(person);
        this.id = person.getId();
        this.personManager = personManager;
    }

    protected Object load() {
        return personManager.get(id);
    }
}
