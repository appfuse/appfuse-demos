package org.appfuse.tutorial.webapp.pages;

import org.apache.wicket.markup.repeater.data.DataView;
import org.appfuse.service.GenericManager;
import org.junit.Test;
import org.springframework.web.context.support.StaticWebApplicationContext;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class PersonListTest extends BasePageTest {

    @Override
    protected void initSpringBeans(StaticWebApplicationContext context) {
        super.initSpringBeans(context);
        context.getBeanFactory().registerSingleton("personManager", mock(GenericManager.class));
    }

    @Test
    public void testRenderPage() {
        tester.startPage(PersonList.class);

        // check that the right page was rendered (no unexpected redirect or intercept)
        tester.assertRenderedPage(PersonList.class);
        // assert that there's no error message
        tester.assertNoErrorMessage();
        // check that the right components are in the page
        tester.assertComponent("persons", DataView.class);
    }
}
