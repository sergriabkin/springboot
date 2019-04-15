package com.company.springboot.service;

import com.company.springboot.entity.Meal;
import com.company.springboot.entity.Person;
import com.company.springboot.repository.MealRepository;
import com.company.springboot.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository repository;
    private final PersonRepository personRepository;

    @Autowired
    public MealServiceImpl(MealRepository repository, PersonRepository personRepository) {
        this.repository = repository;
        this.personRepository = personRepository;
    }

    @Override
    public List<Meal> getAll() {
        return repository.findAll();
    }

    @Override
    public Meal save(String description, Integer calories, Long userId) {
        Person person = personRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        Meal meal = new Meal(LocalDateTime.now(), description, calories, person);
        return repository.save(meal);
    }
    @Override
    public Meal getMeal(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
    @Override
    public Meal updateMeal(Long id, String description,
                    Integer calories, Long userId) {
        repository.deleteById(id);
        Person person = personRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        Meal meal = new Meal(LocalDateTime.now(), description, calories, person);
        return repository.save(meal);
    }
    @Override
    public void deleteMeal(Long id) {
        repository.deleteById(id);
    }

}
