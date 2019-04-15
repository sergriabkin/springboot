package com.company.springboot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@ToString
@Data
@NoArgsConstructor
@Table(name = "MEALS")
public class Meal {

    public Meal(LocalDateTime dateTime, String description, Integer calories, Person person) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.person = person;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "DATE")
    private LocalDateTime dateTime;

    @Column
    private String description;

    @Column
    private Integer calories;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

}
