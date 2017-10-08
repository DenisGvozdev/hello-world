package com.gds.service.personService.interfaces;

import com.gds.dto.PersonDto;
import com.gds.entity.Person;

import java.io.Serializable;
import java.util.UUID;

public interface PersonService extends Serializable{
    PersonDto getById(UUID id);
    PersonDto add(PersonDto PersonDto);
    PersonDto edit(PersonDto person);
    boolean deleteById(UUID id);
}
