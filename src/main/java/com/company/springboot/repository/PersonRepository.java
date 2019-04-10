package com.company.springboot.repository;

import com.company.springboot.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByNameIgnoreCase(String name);

    List<Person> findByNameAndAge(String name, Integer age);

    List<Person> findByAgeOrderByName(Integer age);

    List<Person> findByNameLike(String name);

    List<Person> findByAgeBetween(Integer from, Integer to);

}
