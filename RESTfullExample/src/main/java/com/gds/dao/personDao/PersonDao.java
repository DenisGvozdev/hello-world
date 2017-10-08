package com.gds.dao.personDao;

import com.gds.entity.Person;

import java.util.UUID;

public interface PersonDao {
    Person getById(UUID id);
    Person add(Person person);
    Person edit(Person person);
    boolean deleteById(UUID id);
}
