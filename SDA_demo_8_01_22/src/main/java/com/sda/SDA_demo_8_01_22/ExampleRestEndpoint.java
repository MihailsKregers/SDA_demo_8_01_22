package com.sda.SDA_demo_8_01_22;

import com.sda.SDA_demo_8_01_22.entities.Person;
import com.sda.SDA_demo_8_01_22.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

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
    public String savePersonToDatabase(@RequestBody Person person) {
        personRepository.save(person);
        return "Successfully saved!";
    }

    @GetMapping(path = "/personsFromDatabase", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String personsFromDatabase() {
        return personRepository.findAll().toString();
    }

}
