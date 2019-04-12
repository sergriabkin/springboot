package com.company.springboot.controller;

import com.company.springboot.entity.Person;
import com.company.springboot.entity.Phone;
import com.company.springboot.repository.PersonRepository;
import com.company.springboot.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/persons")
public class PersonRestController {

    private final PersonRepository repository;
    private final PhoneRepository phoneRepository;

    @Autowired
    public PersonRestController(PersonRepository repository, PhoneRepository phoneRepository) {
        this.repository = repository;
        this.phoneRepository = phoneRepository;
    }

    //http://localhost:8080/persons/
    @GetMapping()
    List<Person> findAll(){
        return repository.findAll();
    }

    @PostMapping("/save")
    Person save(@RequestParam String name, @RequestParam Integer age){
        Person person = new Person(name, age);
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

    //http://localhost:8080/persons/phones
    @GetMapping("/phones")
    List<Phone> findAllPhones(){
        return phoneRepository.findAll();
    }

    //http://localhost:8080/persons/phones/Vasya
    @GetMapping("/phones/{name}")
    List<Phone> findAllPhones(@PathVariable String name){
        return phoneRepository.findAllByPersonNameOrderByNumber(name);
    }

}
