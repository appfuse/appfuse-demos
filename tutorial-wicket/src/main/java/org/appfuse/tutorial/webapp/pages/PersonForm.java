package org.appfuse.tutorial.webapp.pages;

import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.appfuse.service.GenericManager;
import org.appfuse.tutorial.model.Person;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("personform")
public class PersonForm extends AbstractWebPage {
    @SpringBean(name = "personManager")
    private GenericManager<Person, Long> personManager;
    private final Page responsePage;

    public PersonForm() {
        this(new PersonList(), new Person());
    }

    /**
     * Constructor used to edit an person
     *
     * @param responsePage page to navigate to after this page completes its work
     * @param person       person to edit
     */
    public PersonForm(final Page responsePage, Person person) {
        this.responsePage = responsePage;

        // Add feedback panel for error messages
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);
        feedbackPanel.setVisible(false);

        // Create and add the form
        EditForm form = new EditForm("person-form", person) {
            protected void onSave(Person person) {
                onSavePerson(person);
            }

            protected void onCancel() {
                onCancelEditing();
            }

            protected void onDelete(Person person) {
                onDeletePerson(person);
            }
        };
        add(form);
    }

    /**
     * Listener method for save action
     *
     * @param person person bean
     */
    protected void onSavePerson(Person person) {
        boolean isNew = false;
        if (person.getId() == null) {
            isNew = true;
        }

        try {
            personManager.save(person);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error saving person: " + e.getMessage());
        }

        String message = getLocalizer().getString("person.updated", this);
        if (isNew) {
            message = getLocalizer().getString("person.added", this);
        }
        getSession().info(message);
        if (isNew) {
            FeedbackPanel feedback = (FeedbackPanel) responsePage.get("feedback");
            feedback.setVisible(true);
            feedback.setEscapeModelStrings(true);
            throw new RestartResponseAtInterceptPageException(responsePage);
        } else {
            FeedbackPanel feedback = (FeedbackPanel) this.get("feedback");
            feedback.setVisible(true);
            feedback.setEscapeModelStrings(true);
        }
    }

    /**
     * Listener method for delete action
     *
     * @param person person bean
     */
    protected void onDeletePerson(Person person) {
        personManager.remove(person.getId());

        String message = getLocalizer().getString("person.deleted", this);
        getSession().info(message);

        responsePage.get("feedback").setVisible(true);
        // how to redirect in Wicket 6: http://stackoverflow.com/a/23960578/65681
        throw new RestartResponseAtInterceptPageException(responsePage);
    }

    /**
     * Lister method for cancel action
     */
    private void onCancelEditing() {
        setResponsePage(responsePage);
    }

    /**
     * Subclass of Form used to edit an person
     */
    private static abstract class EditForm extends Form {
        /**
         * Convenience method that adds and prepares a form component
         *
         * @param fc    form component
         * @param label IModel containing the string used in ${label} variable of
         *              validation messages
         */
        private void add(FormComponent fc, IModel<String> label) {
            // Add the component to the form
            super.add(fc);
            // Set its label model
            fc.setLabel(label);
            // Add feedback panel that will be used to display component errors
            add(new ComponentFeedbackPanel(fc.getId() + "-feedback", fc));
        }

        /**
         * Constructor
         *
         * @param id     component id
         * @param person Person object that will be used as a form bean
         */
        public EditForm(String id, Person person) {
            /*
             * Wrap the person bean with a CompoundPropertyModel, this allows
             * us to easily connect form components to the bean properties
             * (component id is used as the property expression)
             */
            super(id, new CompoundPropertyModel<>(person));
            add(new RequiredTextField<>("firstName"), new ResourceModel("person.firstName"));
            add(new RequiredTextField<>("lastName"), new ResourceModel("person.lastName"));

            add(new Button("save", new StringResourceModel("button.save", this, null)) {
                public void onSubmit() {
                    onSave((Person) getForm().getModelObject());
                }
            });

            Button delete = new Button("delete", new StringResourceModel("button.delete", this, null)) {
                public void onSubmit() {
                    onDelete((Person) getForm().getModelObject());
                }
            };

            if (person.getId() == null) {
                delete.setVisible(false);
                delete.setEnabled(false);
            }
            add(delete);

            /*
             * Notice the setDefaultFormProcessing(false) call at the end. This
             * tells wicket that when this button is pressed it should not
             * perform any form processing (ie bind request values to the bean).
            */
            add(new Button("cancel", new StringResourceModel("button.cancel", this, null)) {
                public void onSubmit() {
                    onCancel();
                }
            }.setDefaultFormProcessing(false));
        }

        /**
         * Callback for cancel button
         */
        protected abstract void onCancel();

        /**
         * Callback for delete button
         *
         * @param person person bean
         */
        protected abstract void onDelete(Person person);

        /**
         * Callback for save button
         *
         * @param person person bean
         */
        protected abstract void onSave(Person person);
    }
}
