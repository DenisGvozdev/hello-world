package com.gds.dao.personDao;

import com.gds.entity.Person;
import com.gds.persistence.HibernateUtil;
import org.hibernate.Session;

import java.util.UUID;

public class PersonDaoImpl implements PersonDao {
    @Override
    public Person getById(UUID id) {
        return null;
    }

    @Override
    public Person add(Person person) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        person = (Person) session.save(person);
        session.getTransaction().commit();
        return null;
    }

    @Override
    public Person edit(Person person) {
        return null;
    }

    @Override
    public boolean deleteById(UUID id) {
        return false;
    }
}
