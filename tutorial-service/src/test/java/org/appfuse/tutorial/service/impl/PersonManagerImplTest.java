package org.appfuse.tutorial.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.tutorial.dao.PersonDao;
import org.appfuse.tutorial.model.Person;
import org.appfuse.service.impl.BaseManagerMockTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class PersonManagerImplTest extends BaseManagerMockTestCase {
    private PersonManagerImpl manager = null;
    private PersonDao dao = null;

    @Before
    public void setUp() {
        dao = context.mock(PersonDao.class);
        manager = new PersonManagerImpl(dao);
    }

    @After
    public void tearDown() {
        manager = null;
    }

    @Test
    public void testGetPerson() {
        log.debug("testing get...");

        final Long id = 7L;
        final Person person = new Person();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).get(with(equal(id)));
            will(returnValue(person));
        }});

        Person result = manager.get(id);
        assertSame(person, result);
    }

    @Test
    public void testGetPersons() {
        log.debug("testing getAll...");

        final List persons = new ArrayList();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).getAll();
            will(returnValue(persons));
        }});

        List result = manager.getAll();
        assertSame(persons, result);
    }

    @Test
    public void testSavePerson() {
        log.debug("testing save...");

        final Person person = new Person();
        // enter all required fields
        
        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).save(with(same(person)));
        }});

        manager.save(person);
    }

    @Test
    public void testRemovePerson() {
        log.debug("testing remove...");

        final Long id = -11L;

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).remove(with(equal(id)));
        }});

        manager.remove(id);
    }
}