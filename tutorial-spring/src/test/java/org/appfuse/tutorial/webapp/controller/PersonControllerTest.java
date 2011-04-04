package org.appfuse.tutorial.webapp.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PersonControllerTest extends BaseControllerTestCase {
    @Autowired
    private PersonController controller;

    @Test
    public void testHandleRequest() throws Exception {
        ModelAndView mav = controller.handleRequest();
        ModelMap m = mav.getModelMap();
        assertNotNull(m.get("personList"));
        assertTrue(((List) m.get("personList")).size() > 0);
    }
}