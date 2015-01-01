package org.appfuse.tutorial.service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.appfuse.service.GenericManager;
import org.appfuse.tutorial.model.Person;

/**
 * Person Manager and WebService interface.
 * 
 * @author ivangsa
 */
@WebService
@Path("/people")
public interface PersonManager extends GenericManager<Person, Long> {
    @GET
    @Path("{id}")
    Person get(@PathParam("id") Long id);

    @GET
    @Path("count/{searchTerm}")
    long countPeople(@PathParam("searchTerm") String searchTerm);

    @GET
    @Path("list/{searchTerm}")
    List<Person> getPeople(
            @PathParam("searchTerm") String searchTerm,
            @QueryParam("firstResult") Integer firstResult,
            @QueryParam("maxResults") Integer maxResults);
}