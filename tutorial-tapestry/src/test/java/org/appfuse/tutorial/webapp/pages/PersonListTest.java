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
}