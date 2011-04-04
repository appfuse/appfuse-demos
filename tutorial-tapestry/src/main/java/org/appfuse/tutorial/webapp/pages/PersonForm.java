package org.appfuse.tutorial.webapp.pages;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.appfuse.service.GenericManager;
import org.appfuse.tutorial.model.Person;
import org.slf4j.Logger;

public class PersonForm extends BasePage {
    @Inject
    private Logger log;

    @Inject
    @Service("personManager")
    private GenericManager<Person, Long> personManager;

    void beginRender() {
        if (person == null) {
            person = new Person();
        }
    }

    @Persist
    private Person person;

    public Person getPerson() {
        return person;
    }

    /**
     * Allows setting person object from another class (i.e. PersonList)
     *
     * @param person an initialized instance
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    @InjectPage
    private PersonList personList;

    @Component(id = "personForm")
    private Form form;

    private boolean cancel;
    private boolean delete;

    @Component(id = "firstName", parameters = {"value=person.firstName", "validate=required"})
    private TextField firstNameField;

    @Component(id = "lastName", parameters = {"value=person.lastName", "validate=required"})
    private TextField lastNameField;

    void onValidateForm() {
        if (!delete && !cancel) {
            // manually validate required fields or annotate the Person object
            /*if (foo.getProperty() == null || user.getProperty().trim().equals("")) {
                form.recordError("Property is a required field.");
            }*/
        }
    }

    void onActivate(Long id) {
        if (id != null) {
            person = personManager.get(id);
        }
    }

    Object onSuccess() {
        if (delete) return onDelete();
        if (cancel) return onCancel();

        log.debug("Saving person...");

        boolean isNew = (getPerson().getId() == null);

        personManager.save(person);

        String key = (isNew) ? "person.added" : "person.updated";

        if (isNew) {
            personList.addInfo(key, true);
            return personList;
        } else {
            addInfo(key, true);
            return this;
        }
    }

    void onSelectedFromDelete() {
        log.debug("Deleting person...");
        delete = true;
    }

    void onSelectedFromCancel() {
        log.debug("Cancelling form...");
        cancel = true;
    }

    Object onDelete() {
        personManager.remove(person.getId());
        personList.addInfo("person.deleted", true);
        return personList;
    }

    Object onCancel() {
        return personList;
    }
}