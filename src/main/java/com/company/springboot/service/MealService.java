package com.company.springboot.service;

import com.company.springboot.entity.Meal;

import java.util.List;

public interface MealService {

    List<Meal> getAll();

    Meal save(String description, Integer calories, Long userId);

    Meal getMeal(Long id);

    Meal updateMeal(Long id, String description, Integer calories, Long userId);

    void deleteMeal(Long id);

}
