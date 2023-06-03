package ru.netology.home04.service;

import org.springframework.stereotype.Service;
import ru.netology.home04.dao.Person;
import ru.netology.home04.repository.PersonsRepositoryImpl;

import java.util.List;

@Service
public class PersonService {

    private final PersonsRepositoryImpl repo;

    public PersonService(PersonsRepositoryImpl repo) {
        this.repo = repo;
    }

    public List<Person> getByCity(String city) {
        return repo.getPersonsByCity(city);
    }

}
