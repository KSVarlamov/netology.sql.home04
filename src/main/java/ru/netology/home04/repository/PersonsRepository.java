package ru.netology.home04.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netology.home04.dao.Person;

import java.util.List;
import java.util.Optional;

public interface PersonsRepository extends JpaRepository<Person, Integer> {
    List<Person> getAllByCityEqualsIgnoreCase(String city);

    List<Person> getAllByPk_AgeLessThan(int age);
//создайте метод, который будет принимать имя и фамилию (name и surname) и возвращать Entity из базы данных, которые соответствуют сочетанию name и surname и являются Optional.

    Optional<Person> getPersonByPk_NameAndPk_Surname(String name, String surname);
}
