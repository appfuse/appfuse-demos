package org.appfuse.tutorial.webapp.pages;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.appfuse.service.GenericManager;
import org.appfuse.tutorial.model.Person;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("persons")
public class PersonList extends AbstractWebPage {
    @SpringBean(name = "personManager")
    private GenericManager<Person, Long> personManager;

    public PersonList() {
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);
        feedbackPanel.setVisible(false); // other pages will set this to visible
        feedbackPanel.setEscapeModelStrings(false);

        // Form and button for routing person to add a new person
        Form form = new Form("form");
        Button button = new Button("add-person") {
            public void onSubmit() {
                onEditPerson(new Person());
            }
        };
        button.setDefaultFormProcessing(false);
        form.add(button);
        add(form);

        SortablePersonDataProvider dp = new SortablePersonDataProvider(personManager);

        final DataView<Person> dataView = new DataView<Person>("persons", dp) {
            protected void populateItem(final Item<Person> item) {
                Person person = item.getModelObject();

                Link<Person> link = new Link<Person>("edit-link", item.getModel()) {
                    public void onClick() {
                        onEditPerson(getModelObject());
                    }
                };
                link.add(new Label("person.id", person.getId()));
                item.add(link);
                item.add(new Label("person.firstName", person.getFirstName()));
                item.add(new Label("person.lastName", person.getLastName()));
            }
        };

        dataView.setItemsPerPage(10);

        add(new OrderByBorder<>("orderById", "id", dp));
        add(new OrderByBorder<>("orderByFirstName", "firstName", dp));
        add(new OrderByBorder<>("orderByLastName", "lastName", dp));
        add(dataView);

        add(new PagingNavigator("navigator", dataView));
    }

    /**
     * Listener for the edit action
     *
     * @param person person to be edited
     */
    protected void onEditPerson(Person person) {
        setResponsePage(new PersonForm(this, person));
    }
}
