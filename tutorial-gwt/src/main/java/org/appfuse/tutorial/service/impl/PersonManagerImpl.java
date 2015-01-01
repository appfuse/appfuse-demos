package org.appfuse.tutorial.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.appfuse.dao.GenericDao;
import org.appfuse.service.impl.GenericManagerImpl;
import org.appfuse.tutorial.model.Person;
import org.appfuse.tutorial.service.PersonManager;
import org.springframework.stereotype.Service;

/**
 * Person Manager and WebService.
 * 
 * @author ivangsa
 */

@Service("personManager")
@WebService(serviceName = "PersonService", endpointInterface = "org.appfuse.tutorial.service.PersonManager")
public class PersonManagerImpl extends GenericManagerImpl<Person, Long> implements PersonManager {

    @Resource(name = "personDao")
    public void setPersonDao(GenericDao<Person, Long> personDao) {
        this.dao = personDao;
    }

    private List<Person> searchPeople(String searchTerm) {
        if (StringUtils.isNotBlank(searchTerm)) {
            return dao.search(searchTerm);
        } else {
            return dao.getAll();
        }
    }

    public long countPeople(String searchTerm) {
        return searchPeople(searchTerm).size();
    }

    public List<Person> getPeople(String searchTerm, Integer firstResult, Integer maxResults) {
        List<Person> people = searchPeople(searchTerm);
        if (firstResult != null || maxResults != null) {
            int fromIndex = firstResult != null ? Math.min(firstResult, people.size()) : 0;
            int toIndex = maxResults != null ? Math.min(fromIndex + maxResults, people.size()) : people.size();
            return people.subList(fromIndex, toIndex);
        }
        return people;
    }
}