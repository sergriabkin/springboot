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
    }

    @GetMapping("/persons")
    List<Person> findAll(){
        return repository.findAll();
    }

//    @PostMapping("/persons")
//    Person person(@RequestBody Person newPerson) {
//        return repository.save(newPerson);

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
