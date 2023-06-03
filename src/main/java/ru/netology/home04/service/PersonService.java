package ru.netology.home04.service;

import org.springframework.stereotype.Service;
import ru.netology.home04.dao.Person;
import ru.netology.home04.repository.PersonsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonsRepository repo;

    public PersonService(PersonsRepository repo) {
        this.repo = repo;
    }


    public List<Person> getByCity(String city) {
        return repo.getAllByCityEqualsIgnoreCase(city);
    }

    public List<Person> getByAgeLess(int age) {
        return repo.getAllByPk_AgeLessThan(age);
    }

    public Optional<Person> getByNameAndSurname(String name, String surname) {
        return repo.getPersonByPk_NameAndPk_Surname(name, surname);
    }

}
