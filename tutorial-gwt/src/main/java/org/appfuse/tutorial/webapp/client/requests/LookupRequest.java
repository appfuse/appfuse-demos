/**
 * 
 */
package org.appfuse.tutorial.webapp.client.requests;

import org.appfuse.tutorial.webapp.client.proxies.LookupConstantsProxy;
import org.appfuse.tutorial.webapp.server.GwtServiceLocator;
import org.appfuse.tutorial.webapp.server.services.LookupRequestService;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

/**
 * @author ivangsa
 *
 */
@Service(value = LookupRequestService.class, locator = GwtServiceLocator.class)
public interface LookupRequest extends RequestContext {

    public Request<LookupConstantsProxy> getApplicationConstants();

    public Request<LookupConstantsProxy> reloadOptions();
}
