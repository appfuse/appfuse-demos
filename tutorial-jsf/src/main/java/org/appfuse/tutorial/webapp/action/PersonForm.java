package org.appfuse.tutorial.webapp.action;

import java.io.Serializable;
import org.appfuse.tutorial.model.Person;
import org.appfuse.service.GenericManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("request")
@Component("personForm")
public class PersonForm extends BasePage implements Serializable {
    private GenericManager<Person, Long> personManager;
    private Person person = new Person();
    private Long id;

    @Autowired
    public void setPersonManager(@Qualifier("personManager") GenericManager<Person, Long> manager) {
        this.personManager = manager;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String delete() {
        personManager.remove(person.getId());
        addMessage("person.deleted");

        return "list";
    }

    public String edit() {
        if (id == null) {
            id = new Long(getParameter("id"));
        }

        person = personManager.get(id);

        return "edit";
    }

    public String save() {
        boolean isNew = (person.getId() == null || person.getId() == 0);
        personManager.save(person);

        String key = (isNew) ? "person.added" : "person.updated";
        addMessage(key);

        if (isNew) {
            return "list";
        } else {
            return "edit";
        }
    }
} 
