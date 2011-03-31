package org.appfuse.tutorial.service;

import org.appfuse.service.GenericManager;
import org.appfuse.tutorial.model.Person;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@WebService
@Path("/")
public interface PersonManager extends GenericManager<Person, Long> {
    @Path("/person/{lastname}")
    @GET
    List<Person> findByLastName(@PathParam("lastname") String lastName);

    @Path("/people")
    @GET
    List<Person> getPeople();
}