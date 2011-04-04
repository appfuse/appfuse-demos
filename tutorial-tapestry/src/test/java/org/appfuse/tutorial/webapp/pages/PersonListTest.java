package org.appfuse.tutorial.webapp.pages;

import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Node;
import org.junit.Test;

import java.util.List;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

public class PersonListTest extends BasePageTestCase {
    @Test
    public void testList() {
        doc = tester.renderPage("personList");
        assertNotNull(doc.getElementById("personList"));
        assertTrue(doc.getElementById("personList").find("tbody").getChildren().size() >= 2);
    }

    @Test
    public void testEdit() {
        doc = tester.renderPage("personList");

        Element table = doc.getElementById("personList");
        List<Node> rows = table.find("tbody").getChildren();
        String id = ((Element) rows.get(0)).find("td/a").getChildMarkup().trim();
        Element editLink = table.getElementById("person-" + id);
        doc = tester.clickLink(editLink);

        ResourceBundle rb = ResourceBundle.getBundle(MESSAGES);

        assertTrue(doc.toString().contains("<title>" +
                rb.getString("personDetail.title")));
    }
}
