package com.gds.rest;

import com.gds.dto.PersonDto;
import com.gds.service.personService.impl.PersonServiceImpl;
import com.gds.entity.Person;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/personResource")
public class PersonResource {

    @Inject
    PersonServiceImpl personService;

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
        inputPerson = personService.add(inputPerson);
        return Response.status(200).entity(inputPerson).build();
    }

    /**
     * Пример вызова: http://localhost:8080/rest/personService/createFromFormParams
     * @param name это парамер который отправлен через клиентскую форму
     * @return JSON объект
     */
    @POST
    @Path("/createFromFormParams")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response createFromFormParams(@FormParam("name") String name){
        Person newPerson = new Person(name);
        return Response.status(200).entity(newPerson).build();
    }

    /**
     * Пример вызова: http://localhost:8080/rest/personService/getById?id=7604e5b7-2a5a-402a-81b6-83d9784596f9
     * @param id идентификатор объекта
     * @return JSON объект
     */
    @GET
    @Path("/getById")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByQueryId(@QueryParam("id") UUID id) {
        Person foundPerson = new Person("Petya");
        return Response.status(200).entity(foundPerson).build();
    }

    /**
     * Пример вызова http://localhost:8080/rest/personService/getById/7604e5b7-2a5a-402a-81b6-83d9784596f9
     * @param id идентификатор объекта
     * @return JSON объект
     */
    @GET
    @Path("/getById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByPathId(@PathParam("id") UUID id) {
        Person foundPerson = new Person("Petya");
        return Response.status(200).entity(foundPerson).build();
    }

    /**
     * Пример вызова: http://localhost:8080/rest/personService/deleteById?id=7604e5b7-2a5a-402a-81b6-83d9784596f9
     * @param id идентификатор объекта
     * @return boolean значение
     */
    @DELETE
    @Path("/deleteById")
    public Response deleteByQueryId(@QueryParam("id") UUID id){
        return Response.status(200).entity("Данные успешно удалены").build();
    }
}
