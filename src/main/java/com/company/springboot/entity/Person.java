package com.company.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@ToString
@Data
@NoArgsConstructor
public class Person {

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Integer age;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private List<Phone> phones;


    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private List<Meal> meals;


}
