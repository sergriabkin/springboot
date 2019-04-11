package com.company.springboot.controller;

import com.company.springboot.entity.Person;
import com.company.springboot.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/persons")
public class PersonRestController {

    private final PersonRepository repository;

    @Autowired
    public PersonRestController(PersonRepository repository) {
        this.repository = repository;
    }

    //http://localhost:8080/persons/
    @GetMapping()
    List<Person> findAll(){
        return repository.findAll();
    }

    //http://localhost:8080/persons/save/Petro&20
    @GetMapping("/save/{req}")
    Person save(@PathVariable String req){
        String[] params = req.split("&");
        Person person = new Person(params[0], Integer.valueOf(params[1]));
        return repository.save(person);
    }

    //http://localhost:8080/persons/1001
    @GetMapping("/{id}")
    Person findById(@PathVariable Long id){
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
//not working:
    //http://localhost:8080/persons/findByNameIgnoreCase/jiM
    @GetMapping("/findByNameIgnoreCase/{name}")
    List<Person> findByNameIgnoreCase(@PathVariable String name){
        return repository.findByNameIgnoreCase(name);
    }

    //http://localhost:8080/persons/findByNameAndAge/Vasya&20
    @GetMapping("/findByNameAndAge/{req}")
    List<Person> findByNameAndAge(@PathVariable String req){
        String[] params = req.split("&");
        return repository.findByNameAndAge(params[0], Integer.valueOf(params[1]));
    }
//not working:
    //http://localhost:8080/persons/findByAgeOrderByName/20
    @GetMapping("/findByNameIgnoreCase/{age}")
    List<Person> findByAgeOrderByName(@PathVariable Integer age){
        return repository.findByAgeOrderByName(age);
    }

    //http://localhost:8080/persons/findByNameLike/ya
    @GetMapping("/findByNameLike/{name}")
    List<Person> findByNameLike(@PathVariable String name){
        return repository.findByNameLike("%"+name+"%");
    }

    //http://localhost:8080/persons/findByAgeBetween/20&30
    @GetMapping("/findByAgeBetween/{req}")
    List<Person> findByAgeBetween(@PathVariable String req){
        String[] params = req.split("&");
        return repository.findByAgeBetween(Integer.valueOf(params[0]), Integer.valueOf(params[1]));
    }


}
