package org.appfuse.tutorial.webapp.pages;

import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Node;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.ResourceBundle;

public class PersonFormTest extends BasePageTestCase {

    @Test
    public void testCancel() throws Exception {
        doc = tester.renderPage("personList");
        Element table = doc.getElementById("personList");
        List<Node> rows = table.find("tbody").getChildren();
        String id = ((Element) rows.get(0)).find("td/a").getChildMarkup().trim();

        Element editLink = table.getElementById("person-" + id);
        doc = tester.clickLink(editLink);

        Element cancelButton = doc.getElementById("cancel");
        doc = tester.clickSubmit(cancelButton, fieldValues);

        ResourceBundle rb = ResourceBundle.getBundle(MESSAGES);

        assertTrue(doc.toString().contains("<title>" +
                rb.getString("personList.title")));
    }

    @Test
    public void testSave() throws Exception {
        doc = tester.renderPage("PersonForm");

        Element form = doc.getElementById("personForm");
        assertNotNull(form);

        // enter all required fields
        fieldValues.put("firstName", "Jack");
        fieldValues.put("lastName", "Raible");

        doc = tester.submitForm(form, fieldValues);

        Element errors = doc.getElementById("errorMessages");

        if (errors != null) {
            System.out.println(errors);
        }

        assertNull(doc.getElementById("errorMessages"));

        Element successMessages = doc.getElementById("successMessages");
        assertNotNull(successMessages);
        assertTrue(successMessages.toString().contains("added successfully"));
        Element table = doc.getElementById("personList");
        assertNotNull(table);
    }

    @Test
    public void testRemove() throws Exception {
        doc = tester.renderPage("personList");
        Element table = doc.getElementById("personList");
        List<Node> rows = table.find("tbody").getChildren();
        String id = ((Element) rows.get(1)).find("td/a").getChildMarkup().trim();

        Element editLink = table.getElementById("person-" + id);
        doc = tester.clickLink(editLink);

        Element deleteButton = doc.getElementById("delete");
        doc = tester.clickSubmit(deleteButton, fieldValues);
        assertTrue(doc.toString().contains("deleted successfully"));
    }
}