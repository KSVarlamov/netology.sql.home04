package ru.netology.home04.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.netology.home04.dao.Person;

import java.util.List;
import java.util.Optional;

public interface PersonsRepository extends JpaRepository<Person, Integer> {

    @Query("SELECT p FROM Person p where lower(p.city) = :city")
    List<Person> getAllByCity(String city);

    @Query("SELECT p FROM Person p where p.pk.age < :age")
    List<Person> getAllAgeLessThan(int age);

    @Query("SELECT p FROM Person p where p.pk.name = :name and p.pk.surname = :surname")
    Optional<Person> getPersonByFullName(String name, String surname);
}
