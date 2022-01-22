package com.sda.SDA_demo_8_01_22;

import com.sda.SDA_demo_8_01_22.entities.Person;
import com.sda.SDA_demo_8_01_22.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Controller
public class ExampleRestEndpoint {

    private PersonRepository personRepository;

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    private static List<Person> persons;

    public static List<Person> getPersons() {
        if (persons == null) {
            persons = new LinkedList<Person>();
        }
        return persons;
    }

    @PostMapping(path = "/postPerson", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String postPerson(@RequestBody Person person) {
        getPersons().add(person);
        return getPersons().toString();
    }

    @GetMapping(path = "/helloWorld", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String restHelloWorld() {
        return "Hello world from REST!";
    }

    @GetMapping(path = "/add", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String add(@RequestParam Integer a, @RequestParam Integer b) {
        Integer result = a + b;
        return result.toString();
    }

    @PostMapping(path = "/personPrint", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String personPrint(@RequestBody Person person) {
        person.setLastName(
                "Modified " + person.getLastName()
        );
        return person.toString();
    }

    @PostMapping(path = "/receiveText", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String personPrint(@RequestBody String someText) {
        return "Received " + someText;
    }

    @PostMapping(path = "/savePersonToDatabase", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String savePersonToDatabase(@Valid @RequestBody Person person, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessages = "";
            for (FieldError error : result.getFieldErrors()) {
                errorMessages += error.getField() + ": " + error.getDefaultMessage() + "\n";
            }
            return errorMessages;
        }
        personRepository.save(person);
        return "Successfully saved!";
    }

    @GetMapping(path = "/personsFromDatabase", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String personsFromDatabase() {
        return personRepository.findAll().toString();
    }

    @GetMapping(path = "/personsFromDatabaseByName", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String personsFromDatabase(@RequestParam String name) {
        return personRepository.findByFirstName(name).toString();
    }

    @GetMapping(path = "/personsFromDatabaseByIdGt", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String personsFromDatabase(@RequestParam Long id) {
        return personRepository.findByIdGreaterThan(id).toString();
    }

    private static Set<String> excludedNames;

    public static Set<String> getExcludedNames() {
        if (excludedNames == null) {
            excludedNames = new LinkedHashSet<String>();
        }
        return excludedNames;
    }

    @GetMapping(path = "/excludeName", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String excludeName(@RequestParam String name) {
        getExcludedNames().add(name);
        return "Successfully excluded name " + name;
    }

    @GetMapping(path = "/allowName", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String allowName(@RequestParam String name) {
        getExcludedNames().remove(name);
        return "Successfully allowed name " + name;
    }

    @GetMapping(path = "/getPersonsWithExclusions", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String getPersonsWithExclusions() {
        if (getExcludedNames().isEmpty()) {
            return personRepository.findAll().toString();
        }
        return personRepository.findByFirstNameNotIn(getExcludedNames()).toString();
    }
}
