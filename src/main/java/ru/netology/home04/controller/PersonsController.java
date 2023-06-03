package ru.netology.home04.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.home04.service.PersonService;

@RestController
@RequestMapping("/api/persons/")
public class PersonsController {

    private final PersonService service;

    public PersonsController(PersonService service) {
        this.service = service;
    }


    @GetMapping("by-city")
    @ResponseBody
    Object getByCity(@RequestParam String city) {
        return service.getByCity(city.toLowerCase());
    }

}
