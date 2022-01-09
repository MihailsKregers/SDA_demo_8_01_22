package com.sda.SDA_demo_8_01_22.repositories;

import com.sda.SDA_demo_8_01_22.entities.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface PersonRepository extends CrudRepository<Person, Long> {

    public List<Person> findByFirstName(String firstName);

    List<Person> findByIdGreaterThan(Long id);

    List<Person> findByFirstNameNotIn(Set<String> names);
}
