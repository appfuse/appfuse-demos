package org.appfuse.crud.action;

import org.appfuse.crud.model.Person;
import org.appfuse.service.GenericManager;
import org.appfuse.webapp.action.BaseAction;

import java.util.List;
import java.text.MessageFormat;

/**
 * @author mraible
 */
public class PersonAction extends BaseAction {
    GenericManager<Person, Long> manager;
    Person person;
    Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public void setPersonManager(GenericManager<Person, Long> genericManager) {
        this.manager = genericManager;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String execute() {
        if (id != null) {
            person = manager.get(id);
        }
        return "success";
    }

    public String save() {
        manager.save(person);
        super.saveMessage("Person updated successfully!");
        return "form";
    }

    private List persons;

    public List getPersons() {
        return this.persons;
    }
    
    public String list() {
        persons = manager.getAll();
        return SUCCESS;
    }

    public String delete() {
        manager.remove(person.getId());
        saveMessage(MessageFormat.format("{0} removed successfully.",
                person.getFirstName() + ' ' + person.getLastName()));
        return "form";
    }
}
