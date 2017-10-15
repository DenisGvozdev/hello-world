package com.gds.rest;

import com.gds.dto.PersonDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/personResource")
public class PersonResource {

    /**
     * Пример вызова: http://localhost:8080/rest/personService/create
     * Также необходимо передать JSON объект {"name":"Petya"}
     * @param inputPerson это JSON объект
     * @return JSON объект
     */
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(PersonDto inputPerson){
        //inputPerson = personService.add(inputPerson);
        return Response.status(200).entity(inputPerson).build();
    }


}
