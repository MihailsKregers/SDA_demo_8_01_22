package com.sda.SDA_demo_8_01_22;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExampleRestEndpoint {

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
}
