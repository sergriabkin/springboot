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

    @GetMapping()
    List<Person> getPeople() {
        return repository.findAll();
    }

    @PostMapping("/save")
    Person savePerson(@RequestParam String name, @RequestParam Integer age) {
        Person person = new Person(name, age);
        return repository.save(person);
    }

    @GetMapping("/{id}")
    Person getParson(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @PutMapping("/update")
    Person updatePerson(@RequestParam Long id, @RequestParam String name, @RequestParam Integer age) {
        repository.deleteById(id);
        return repository.save(new Person(name, age));
    }

    @DeleteMapping("/delete")
    void deletePerson(@RequestParam Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/findByNameIgnoreCase/{name}")
    List<Person> findByNameIgnoreCase(@PathVariable String name) {
        return repository.findByNameIgnoreCase(name);
    }

    @GetMapping("/findByNameAndAge/{req}")
    List<Person> findByNameAndAge(@PathVariable String req) {
        String[] params = req.split("&");
        return repository.findByNameAndAge(params[0], Integer.valueOf(params[1]));
    }

    @GetMapping("/findByAgeOrderByName/{age}")
    List<Person> findByAgeOrderByName(@PathVariable Integer age) {
        return repository.findByAgeOrderByName(age);
    }

    @GetMapping("/findByNameLike/{name}")
    List<Person> findByNameLike(@PathVariable String name) {
        return repository.findByNameLike("%" + name + "%");
    }

    @GetMapping("/findByAgeBetween/{req}")
    List<Person> findByAgeBetween(@PathVariable String req) {
        String[] params = req.split("&");
        return repository.findByAgeBetween(Integer.valueOf(params[0]), Integer.valueOf(params[1]));
    }

    @GetMapping("/phones")
    List<Phone> findAllPhones() {
        return phoneRepository.findAll();
    }

    @GetMapping("/phones/{name}")
    List<Phone> findAllPhones(@PathVariable String name) {
        return phoneRepository.findAllByPersonNameOrderByNumber(name);
    }

}
