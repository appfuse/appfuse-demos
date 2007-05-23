package org.appfuse.tutorial.service.impl;

import org.appfuse.service.impl.BaseManagerMockTestCase;
import org.appfuse.tutorial.dao.PersonDao;
import org.appfuse.tutorial.model.Person;
import org.jmock.Mock;

import java.util.ArrayList;
import java.util.List;

public class PersonManagerImplTest extends BaseManagerMockTestCase {
    private PersonManagerImpl manager = null;
    private Mock dao = null;
    private Person person = null;

    protected void setUp() throws Exception {
        dao = new Mock(PersonDao.class);
        manager = new PersonManagerImpl((PersonDao) dao.proxy());
    }

    protected void tearDown() throws Exception {
        manager = null;
    }

    public void testGetPerson() {
        log.debug("testing getPerson");

        Long id = 7L;
        person = new Person();

        // set expected behavior on dao
        dao.expects(once()).method("get")
                .with(eq(id))
                .will(returnValue(person));

        Person result = manager.get(id);
        assertSame(person, result);
        dao.verify();
    }

    public void testGetPersons() {
        log.debug("testing getPersons");

        List people = new ArrayList();

        // set expected behavior on dao
        dao.expects(once()).method("getAll")
                .will(returnValue(people));

        List result = manager.getAll();
        assertSame(people, result);
        dao.verify();
    }

    public void testFindByLastName() {
        log.debug("testing findByLastName");

        List people = new ArrayList();
        String lastName = "Smith";

        // set expected behavior on dao
        dao.expects(once()).method("findByLastName")
                .with(eq(lastName))
                .will(returnValue(people));

        List result = manager.findByLastName(lastName);
        assertSame(people, result);
        dao.verify();
    }

    public void testSavePerson() {
        log.debug("testing savePerson");

        person = new Person();

        // set expected behavior on dao
        dao.expects(once()).method("save")
                .with(same(person))
                .will(returnValue(person));

        manager.save(person);
        dao.verify();
    }

    public void testRemovePerson() {
        log.debug("testing removePerson");

        Long id = 11L;
        person = new Person();

        // set expected behavior on dao
        dao.expects(once()).method("remove")
                .with(eq(id))
                .isVoid();

        manager.remove(id);
        dao.verify();
    }
} 