package com.company.springboot.controller;

import com.company.springboot.entity.Person;
import com.company.springboot.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping(value = "/persons")
public class PersonRestController {

    private final PersonRepository repository;

    @Autowired
    public PersonRestController(PersonRepository repository) {
        this.repository = repository;
        repository.save(new Person("Jim", 30));
        repository.save(new Person("John", 35));
//        data.sql :
//        INSERT INTO PERSON (ID , NAME , AGE) VALUES
//                (3, 'Vasya', 20),
//                (4, 'Petya', 25);

    }

    @GetMapping("/persons")
    List<Person> findAll(){
        return repository.findAll();
    }

    @PostMapping("/persons")
    Person save(@RequestBody String name, Integer age){
        Person person = new Person(name, age);
        return repository.save(person);
    }

    @GetMapping("/persons/{id}")
    Person findById(@PathVariable Long id){
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

}
