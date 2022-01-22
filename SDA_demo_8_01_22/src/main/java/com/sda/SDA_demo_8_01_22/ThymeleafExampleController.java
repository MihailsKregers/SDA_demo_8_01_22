package com.sda.SDA_demo_8_01_22;

import com.sda.SDA_demo_8_01_22.entities.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ThymeleafExampleController {


    @GetMapping(path = "/thHelloWorld")
    public String thHelloWorld() {
        return "exampleView";
    }

    @GetMapping(path = "/getDataFromThymeleaf")
    public String getDataFromThymeleaf(Model model) {
        model.addAttribute("someAttribute", "TEXT TO THYMELEAF FROM JAVA");
        model.addAttribute("person", new Person());
        return "viewWithData";
    }


    @GetMapping(path = "/postPersonThymeleaf")
    public String postPersonThymeleaf(Model model) {
        model.addAttribute("person", new Person());
        return "personForm";
    }

    @PostMapping(path = "/postPersonThymeleaf")
    public String postPersonThymeleaf(@Valid Person person, Model model) {
        person.setLastName("HelloFromServer");
        model.addAttribute("person", person);
        return "personForm";
    }
}
