package com.company.springboot.entity;

import javax.persistence.*;

@Entity
@Table(name = "PHONES")
public class Phone {

    public Phone() {
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String number;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
