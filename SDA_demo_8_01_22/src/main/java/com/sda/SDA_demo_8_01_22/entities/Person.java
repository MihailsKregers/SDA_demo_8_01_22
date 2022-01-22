package com.sda.SDA_demo_8_01_22.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Pattern(regexp = "^[a-zA-z]*$", message = "Name should contain only alphabet characters!")
    @Pattern(regexp = "^.{3}$", message = "Name should be of length 3!")
    @NotBlank(message = "Name should be not blank!")
    private String firstName;
    @Length(message = "Surname should be min 5 symbols!", min = 5)
    @NotBlank(message = "Surname should be not blank!")
    private String lastName;
    @Pattern(regexp = "^\\d{6}-\\d{5}$", message = "Person code should be of format ******-*****! (* - digits)")
    private String personCode;

    public Person(String firstName, String lastName, String personCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personCode = personCode;
    }

    public Long getId() {
        return id;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personCode='" + personCode + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }
}
