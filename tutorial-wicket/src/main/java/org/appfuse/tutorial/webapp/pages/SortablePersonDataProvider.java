package org.appfuse.tutorial.webapp.pages;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.appfuse.service.GenericManager;
import org.appfuse.tutorial.model.Person;

public class SortablePersonDataProvider extends SortableDataProvider<Person, String> {
    private GenericManager<Person, Long> personManager;

    public SortablePersonDataProvider(GenericManager personManager) {
        this.personManager = personManager;
    }

    public SortablePersonDataProvider() {
        // default sort
        setSort("firstName", SortOrder.ASCENDING);
    }

    @SuppressWarnings("unchecked")
    public Iterator iterator(long first, long count) {
        List persons = personManager.getAll();
        if (first > 0) {
            persons = persons.subList((int) first, (int) (first + count));
        }

        SortParam sp = getSort();

        if (sp != null) {
            Object sortColumn = sp.getProperty();
            Comparator comparator = new BeanComparator(sortColumn.toString());

            if (!sp.isAscending()) {
                comparator = new ReverseComparator(comparator);
            }

            Collections.sort(persons, comparator);
        }

        return persons.iterator();
    }

    public long size() {
        return personManager.getAll().size();
    }

    @Override
    public IModel<Person> model(Person person) {
        return new PersonModel(person, personManager);
    }
}
