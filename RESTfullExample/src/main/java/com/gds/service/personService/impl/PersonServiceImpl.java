package com.gds.service.personService.impl;

import com.gds.dao.personDao.PersonDao;
import com.gds.dto.PersonDto;
import com.gds.service.personService.interfaces.PersonService;
import com.gds.entity.Person;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.UUID;

@Stateless
@Local(PersonService.class)
public class PersonServiceImpl implements PersonService{

    @Inject
    PersonDao personDao;

    @Override
    public PersonDto getById(UUID id) {
        return null;
    }

    @Override
    public PersonDto add(PersonDto personDto) {
        Person newPerson = new Person();
        newPerson.setName(personDto.getName());
        newPerson = personDao.add(newPerson);
        personDto.setId(newPerson.getId());
        return personDto;
    }

    @Override
    public PersonDto edit(PersonDto person) {
        return null;
    }

    @Override
    public boolean deleteById(UUID id) {
        return false;
    }
}
