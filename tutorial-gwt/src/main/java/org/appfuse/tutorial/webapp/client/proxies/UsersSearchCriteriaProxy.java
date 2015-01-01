/**
 * 
 */
package org.appfuse.tutorial.webapp.client.proxies;

import org.appfuse.tutorial.webapp.server.services.UsersSearchCriteria;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;

/**
 * @author ivangsa
 *
 */
@ProxyFor(UsersSearchCriteria.class)
public interface UsersSearchCriteriaProxy extends ValueProxy {

    String getSearchTerm();

    void setSearchTerm(String searchTerm);
}
