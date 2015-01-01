package org.appfuse.tutorial.webapp.client.ui.people.list;

import java.util.List;

import org.appfuse.tutorial.webapp.client.application.Application;
import org.appfuse.tutorial.webapp.client.application.base.activity.AbstractProxySearchActivity;
import org.appfuse.tutorial.webapp.client.application.utils.tables.CustomColumn;
import org.appfuse.tutorial.webapp.client.application.utils.tables.LocalColumnSortHandler;
import org.appfuse.tutorial.webapp.client.proxies.PersonProxy;
import org.appfuse.tutorial.webapp.client.requests.PersonRequest;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.ColumnSortEvent.Handler;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;

/**
 * @author ivangsa
 *
 */
public class PeopleSearchActivity extends AbstractProxySearchActivity<PersonProxy, String> {
    private Handler sortHandler;

    @Inject
    public PeopleSearchActivity(Application application, PeopleSearchView view) {
        super(application, view, String.class);
        setTitle("People List");
    }

    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
        view.setDelegate(this);
        // Configure local/remote sorting
        // sortHandler = createLocalColumnSortHandler(view.asHasData());
        sortHandler = new ColumnSortEvent.AsyncHandler(view.asHasData());
        view.addColumnSortHandler(sortHandler);

        super.start(panel, eventBus);
    }

    /**
     * @param hasData
     */
    private Handler createLocalColumnSortHandler(final HasData hasData) {
        return new LocalColumnSortHandler<PersonProxy>(hasData) {
            @Override
            public List<PersonProxy> getList() {
                return (List<PersonProxy>) hasData.getVisibleItems();
            }
        };
    }

    private String getPropertyNameForColumn(Column column) {
        if (column instanceof CustomColumn) {
            return ((CustomColumn) column).getPropertyName();
        }
        return null;
    }

    @Override
    protected RequestContext createRequestContext() {
        return requests.personRequest();
    }

    @Override
    protected Request<Long> createCountRequest(RequestContext requestContext, String searchTerm) {
        return ((PersonRequest) requestContext).countPeople(searchTerm);
    }

    @Override
    protected Request<List<PersonProxy>> createSearchRequest(RequestContext requestContext,
            String searchTerm, Range range, ColumnSortList columnSortList)
    {
        String sortProperty = null;
        boolean ascending = true;
        if (columnSortList.size() > 0) {
            Column sortColumn = columnSortList.get(0).getColumn();
            sortProperty = getPropertyNameForColumn(sortColumn);
            ascending = columnSortList.get(0).isAscending();
        }
        return ((PersonRequest) requestContext).getPeople(searchTerm, range.getStart(), range.getLength());
    }

    @Override
    public void onStop() {
        // XXX view.removeColumnSortHandler(sortHandle);
        sortHandler = null;
        super.onStop();
    }
}