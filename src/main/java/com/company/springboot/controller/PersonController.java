package com.company.springboot.controller;

import com.company.springboot.entity.Person;
import com.company.springboot.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class PersonController {

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/personsform")
    public String persons(Map<String, Object> model) {
        List<Person> persons = personRepository.findAll();

        model.put("persons", persons);

        return "personsform";
    }

    @PostMapping("/personsform")
    public String add(@RequestParam String name, @RequestParam Integer age, Map<String, Object> model) {
        Person person = new Person(name, age);

        personRepository.save(person);

        List<Person> persons = personRepository.findAll();

        model.put("persons", persons);

        return "personsform";
    }

    @PostMapping("/personsfilter")
    public String filter(@RequestParam String personsfilter, Map<String, Object> model) {
        List<Person> persons;

        if (personsfilter != null && !personsfilter.isEmpty()) {
            persons = personRepository.findByNameIgnoreCase(personsfilter);
        } else {
            persons = personRepository.findAll();
        }

        model.put("persons", persons);

        return "personsform";
    }

}
