package ru.netology.home04.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.netology.home04.dao.Person;
import ru.netology.home04.dao.PersonPK;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
@SuppressWarnings("unchecked")
public class PersonsRepositoryImpl implements PersonsRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public PersonsRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Person add(Person person) {
        try {
            entityManager.persist(person);
        } catch (Exception e) {
            log.error("Ошибка сохранения в бд: {}", e);
        }
        return person;
    }

    @Override
    @Transactional
    public void delete(Person person) {
        entityManager.remove(person);
    }

    @Override
    public Optional<Person> get(PersonPK pk) {
        return Optional.ofNullable(entityManager.getReference(Person.class, pk));
    }

    @Override
    @Transactional
    public void update(Person person) {
        entityManager.refresh(person);
    }

    @Override
    public List<Person> getPersonsByCity(String city) {
        var queryString = String.format("From Person where lower(city) = '%s'", city);
        var query = entityManager.createQuery(queryString);
        return query.getResultList();
    }

    @Override
    public List<Person> getAll() {
        var query = entityManager.createQuery("from Person");
        return query.getResultList();
    }
}
