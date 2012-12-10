package org.appfuse.tutorial.webapp.pages;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.corelib.components.EventLink;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.EventContext;

import org.appfuse.service.GenericManager;
import org.appfuse.tutorial.model.Person;

import org.slf4j.Logger;

import java.util.List;

public class PersonForm {
    @Inject
    private Logger log;

    @Inject
    @Service("personManager")
    private GenericManager<Person, Long> personManager;

    @Inject
    private AlertManager alertManager;

    @Inject
    private Messages messages;

    @Property(write = false)
    @Persist
    private Person person;


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

    void onValidateForm() {
        if (!delete && !cancel) {
            // manually validate required fields or annotate the Person object
            /*if (foo.getProperty() == null || user.getProperty().trim().equals("")) {
                form.recordError("Property is a required field.");
            }*/
        }
    }

    @Component(id = "firstName", parameters = {"value=person.firstName", "validate=required"})
    private TextField firstNameField;

    @Component(id = "lastName", parameters = {"value=person.lastName", "validate=required"})
    private TextField lastNameField;

    void beginRender() {
        if (person == null) {
            person = new Person();
        }
    }

    void onActivate(EventContext ec) {
        if (ec.getCount() == 0) {
            person = null;
        }
        else if (ec.getCount() == 1) {
            person = personManager.get(ec.get(Long.class, 0));
        }
        else {
            throw new IllegalStateException("Invalid Request");
        }
    }

    Long onPassivate() {
        return   person != null ? person.getId() : null;
    }

    void onPrepare() {
        if (person == null) {
            person = new Person();
        }
    }

    Object onException(Throwable cause) {
        log.error("Exception: " +  cause.getMessage());

        return this;
    }

    Object onSuccess() {
        if (delete) return onDelete();
        if (cancel) return onCancel();

        log.debug("Saving person..." + person);

        boolean isNew = (person.getId() == null);

        personManager.save(person);

        String key = (isNew) ? "person.added" : "person.updated";
        alertManager.alert(Duration.TRANSIENT, Severity.INFO,  messages.get(key));

        if (isNew) {
            return personList;
        } else {
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
        alertManager.alert(Duration.TRANSIENT, Severity.INFO, messages.format("person.deleted"));
        return personList;
    }

    Object onCancel() {
        return personList;
    }
}