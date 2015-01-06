package org.appfuse.tutorial.webapp.client.ui.people.edit;

import org.appfuse.tutorial.webapp.client.application.Application;
import org.appfuse.tutorial.webapp.client.application.base.activity.AbstractProxyEditActivity;
import org.appfuse.tutorial.webapp.client.application.base.place.EntitySearchPlace;
import org.appfuse.tutorial.webapp.client.proxies.PersonProxy;
import org.appfuse.tutorial.webapp.client.requests.PersonRequest;
import org.appfuse.tutorial.webapp.client.ui.home.HomePlace;

import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;

/**
 * @author ivangsa
 *
 */
public class EditPersonActivity extends AbstractProxyEditActivity<PersonProxy> implements EditPersonView.Delegate {

    @Inject
    public EditPersonActivity(Application application, EditPersonView view) {
        super(application, view);
        setTitle(application.getI18n().personDetail_title());
    }

    @Override
    protected RequestContext createProxyRequest() {
        return requests.personRequest();
    }

    @Override
    protected Request<PersonProxy> loadProxyRequest(RequestContext requestContext, String entityId) {
        return ((PersonRequest) requestContext).get(Long.parseLong(entityId));
    }

    @Override
    protected RequestContext saveOrUpdateRequest(RequestContext requestContext, PersonProxy proxy) {
        ((PersonRequest) requestContext).save(proxy);
        return requestContext;
    }

    @Override
    protected RequestContext deleteRequest(RequestContext requestContext, PersonProxy proxy) {
        ((PersonRequest) requestContext).remove(proxy.getId());
        return requestContext;
    }

    @Override
    protected Place previousPlace() {
        return new EntitySearchPlace(PersonProxy.class);
    }

    @Override
    protected Place nextPlace(boolean saved) {
        if (saved) {
            return new EntitySearchPlace(PersonProxy.class);
        } else { // deleted
            return new HomePlace();
        }
    }

}