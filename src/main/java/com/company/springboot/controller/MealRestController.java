package com.company.springboot.controller;

import com.company.springboot.entity.Meal;
import com.company.springboot.entity.Person;
import com.company.springboot.repository.MealRepository;
import com.company.springboot.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/meals")
public class MealRestController {

    private final MealRepository repository;
    private final PersonRepository personRepository;

    @Autowired
    public MealRestController(MealRepository repository, PersonRepository personRepository) {
        this.repository = repository;
        this.personRepository = personRepository;
    }

    @GetMapping
    List<Meal> getAll() {
        return repository.findAll();
    }

    @PostMapping("/save")
    Meal save(@RequestParam String description, @RequestParam Integer calories, @RequestParam Long userId) {
        Person person = personRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        Meal meal = new Meal(LocalDateTime.now(), description, calories, person);
        return repository.save(meal);
    }

    @GetMapping("/{id}")
    Meal getMeal(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }


    @PutMapping("/update")
    Meal updateMeal(@RequestParam Long id, @RequestParam String description,
                        @RequestParam Integer calories, @RequestParam Long userId) {
        repository.deleteById(id);
        Person person = personRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        Meal meal = new Meal(LocalDateTime.now(), description, calories, person);
        return repository.save(meal);
    }

    @DeleteMapping("/delete")
    void deleteMeal(@RequestParam Long id) {
        repository.deleteById(id);
    }



}
