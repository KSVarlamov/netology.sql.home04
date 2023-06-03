package ru.netology.home04;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import ru.netology.home04.controller.PersonsController;
import ru.netology.home04.dao.Person;
import ru.netology.home04.dao.PersonPK;
import ru.netology.home04.repository.PersonsRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegratedTest {

    private static final Person person1 = Person.builder()
            .pk(new PersonPK("Vasya", "Pupkin", 33))
            .phoneNumber("+7000000")
            .city("DefaultCity")
            .build();
    private static final Person person2 = Person.builder()
            .pk(new PersonPK("Vasya", "Ivanoff", 22))
            .phoneNumber("+7000000")
            .city("Moscow")
            .build();
    private static final Person person3 = Person.builder()
            .pk(new PersonPK("Johh", "doe", 16))
            .phoneNumber("+7000000")
            .city("Moscow")
            .build();
    @Autowired
    PersonsController controller;
    @Autowired
    private PersonsRepository repo;
    @Autowired
    private TestRestTemplate template;

    @Test
    @Order(1)
    void addData() {
        repo.add(person1);
        repo.add(person2);
        repo.add(person3);
        var list = repo.getAll();
        assertThat(list).hasSize(3);
    }

    @Test
    void test_moscow() {
        var response = template.getForEntity("/api/persons/by-city?city=Moscow", List.class);
        var list = response.getBody();
        assert list != null;
        assertThat(list)
                .hasSize(2);
    }

    @Test
    void test_someStr() {
        var response = template.getForEntity("/api/persons/by-city?city=asdfkhldfashaskjdfaskdhf", List.class);
        var list = response.getBody();
        assert list != null;
        assertThat(list)
                .isEmpty();
    }

}
