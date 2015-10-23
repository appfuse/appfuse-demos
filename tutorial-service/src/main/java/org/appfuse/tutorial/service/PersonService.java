package org.appfuse.tutorial.service;

import org.appfuse.tutorial.model.Person;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * Created by DMaher on 10/23/15.
 */
@WebService
@Path("/persons")
public interface PersonService {
    @GET
    @Path("{lastname}")
    List<Person> findByLastName(@PathParam("lastname") String lastName);

    @GET
    List<Person> getPeople();
}
