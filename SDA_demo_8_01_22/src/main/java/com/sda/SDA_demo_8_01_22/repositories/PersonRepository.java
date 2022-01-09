package com.sda.SDA_demo_8_01_22.repositories;

import com.sda.SDA_demo_8_01_22.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
