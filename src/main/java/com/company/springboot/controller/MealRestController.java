package com.company.springboot.controller;

import com.company.springboot.entity.Meal;
import com.company.springboot.service.MealService;
import com.company.springboot.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/meals")
public class MealRestController {

    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    @GetMapping
    List<Meal> getAll() {
        return service.getAll();
    }

    @PostMapping("/save")
    Meal save(@RequestParam String description, @RequestParam Integer calories, @RequestParam Long userId) {
        return service.save(description, calories, userId);
    }

    @GetMapping("/{id}")
    Meal getMeal(@PathVariable Long id) {
        return service.getMeal(id);
    }

    @PutMapping("/update")
    Meal updateMeal(@RequestParam Long id, @RequestParam String description,
                    @RequestParam Integer calories, @RequestParam Long userId) {
        return service.updateMeal(id, description, calories, userId);
    }

    @DeleteMapping("/delete")
    void deleteMeal(@RequestParam Long id) {
        service.deleteMeal(id);
    }

    @PostMapping("/findAllByDate")
    List<Meal> findAllByDateTimeBetween(@RequestParam String date,
                                        @RequestParam Long userId) {
        LocalDate localDate = Util.parseLocalDate(date);
        return service.findAllByDate(localDate, userId);
    }

    @PostMapping("/calculateExcess")
    String getMessageWithExcess(@RequestParam String date, @RequestParam Long userId) {
        return service.getMessageWithExcess(date, userId);
    }


}
