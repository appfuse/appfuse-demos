package org.appfuse.tutorial.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.appfuse.tutorial.dao.PersonDao;
import org.appfuse.tutorial.model.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

public class PersonManagerEasyMockTest {
    private final Log log = LogFactory.getLog(PersonManagerImplTest.class);
    private PersonManagerImpl manager = null;
    private PersonDao dao = null;
    private Person person = null;

    @Before
    public void setUp() throws Exception {
        log.debug("setUpDao for PersonManagerImplTest");
        dao = createStrictMock(PersonDao.class);
        manager = new PersonManagerImpl((PersonDao) dao);
    }

    @Test
    public void testGetPerson() {
        log.debug("testing getPerson");

        Long id = 7L;
        person = new Person();

        // set expected behavior on dao
        expect(dao.get(id)).andReturn(person);
        replay(dao);

        Person result = manager.get(id);
        assertSame(person, result);
        verify(dao);
    }

    @Test
    public void testGetPersons() {
        log.debug("testing getPersons");

        List people = new ArrayList();

        // set expected behavior on dao
        expect(dao.getAll()).andReturn(people);
        replay(dao);

        List result = manager.getAll();
        assertSame(people, result);
        verify(dao);
    }

    @Test
    public void testGetByLastName() {
        log.debug("testing getByLastName");

        List people = new ArrayList();
        String lastName = "Smith";

        // set expected behavior on dao
        expect(dao.findByLastName(lastName)).andReturn(people);
        replay(dao);

        List result = manager.findByLastName(lastName);
        assertSame(people, result);
        verify(dao);
    }

    public void testSavePerson() {
        log.debug("testing savePerson");

        person = new Person();

        // set expected behavior on dao
        expect(dao.save(person)).andReturn(person);
        replay(dao);

        manager.save(person);
        verify(dao);
    }

    @Test
    public void testRemovePerson() {
        log.debug("testing removePerson");

        Long id = 11L;
        person = new Person();

        // set expected behavior on dao
        dao.remove(id);
        replay(dao);

        manager.remove(id);
        verify(dao);
    }
}