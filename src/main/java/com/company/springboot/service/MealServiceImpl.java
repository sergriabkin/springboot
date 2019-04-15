package com.company.springboot.service;

import com.company.springboot.entity.Meal;
import com.company.springboot.entity.Person;
import com.company.springboot.repository.MealRepository;
import com.company.springboot.repository.PersonRepository;
import com.company.springboot.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
        Meal meal = new Meal(LocalDate.now(), description, calories, person);
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
        Meal meal = new Meal(LocalDate.now(), description, calories, person);
        return repository.save(meal);
    }
    @Override
    public void deleteMeal(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Meal> findAllByDate(LocalDate localDate, Long userId) {
        return personRepository.findById(userId).orElseThrow(IllegalArgumentException::new)
                .getMeals().stream()
                .filter(meal -> meal.getDate().toString().equals(localDate.toString()))
                .collect(Collectors.toList());
    }

    @Override
    public String getMessageWithExcess(String date, Long userId) {
        LocalDate localDate = Util.parseLocalDate(date);
        Person person = personRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        int caloriesExpected = Util.calculateCaloriesNorma(person);

        int caloriesActual = findAllByDate(localDate, userId).stream()
                .mapToInt(Meal::getCalories)
                .sum();

        int result = caloriesActual - caloriesExpected;

        return  result > 0 ?
                "This day you get "+result+" calories more than needed" :
                "it`s ok, you can even get " + Math.abs(result) + " calories more";

    }


}
