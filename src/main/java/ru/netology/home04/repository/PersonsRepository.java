package ru.netology.home04.repository;

import ru.netology.home04.dao.Person;
import ru.netology.home04.dao.PersonPK;

import java.util.List;
import java.util.Optional;

public interface PersonsRepository {
    Person add(Person person);

    void delete(Person person);

    Optional<Person> get(PersonPK pk);

    void update(Person person);

    List<Person> getPersonsByCity(String city);

    List<Person> getAll();

}
