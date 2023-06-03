package ru.netology.home04.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.home04.dao.Person;
import ru.netology.home04.service.PersonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons/")
public class PersonsController {

    private final PersonService service;

    public PersonsController(PersonService service) {
        this.service = service;
    }


    @GetMapping("by-city")
    @ResponseBody
    List<Person> getByCity(@RequestParam String city) {
        return service.getByCity(city.toLowerCase());
    }

    @GetMapping("age-less")
    @ResponseBody
    List<Person> getByAgeLess(@RequestParam int age) {
        return service.getByAgeLess(age);
    }

    @GetMapping("by-fullname")
    @ResponseBody
    Optional<Person> getByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        return service.getByNameAndSurname(name, surname);
    }


}
