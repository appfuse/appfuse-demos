package org.appfuse.tutorial.webapp.client.requests;

import java.util.List;

import org.appfuse.tutorial.service.PersonManager;
import org.appfuse.tutorial.webapp.client.proxies.PersonProxy;
import org.appfuse.tutorial.webapp.server.GwtServiceLocator;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

/**
 * @author ivangsa
 *
 */
@Service(value = PersonManager.class, locator = GwtServiceLocator.class)
public interface PersonRequest extends RequestContext {
    Request<PersonProxy> get(Long id);

    Request<PersonProxy> save(PersonProxy object);

    Request<Void> remove(Long id);

    Request<Long> countPeople(String searchTerm);

    Request<List<PersonProxy>> getPeople(String searchTerm, Integer firstResult, Integer maxResults);
}