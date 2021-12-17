package com.rp.circuitbreaker.controllers;

import com.rp.circuitbreaker.model.Greeting;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class GreetingController {
    Map<String, String> greetingStore = new HashMap<>();

    @GetMapping(value = "/greet/{country}", produces = "application/json")
    public Greeting indianGreeting(@PathVariable("country")String countryName){
        System.out.println("CountryName : "+countryName);
        Greeting greeting = new Greeting();
        String default_greet = greetingStore.getOrDefault(countryName, "Default Greet");
        greeting.setKey(countryName);
        greeting.setValue(default_greet);
        return greeting;
    }

    @PostMapping(value = "/greet", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> saveGreeting(@RequestBody Greeting greeting){
        String nation = greeting.getKey();
        String greet = greeting.getValue();
        greetingStore.put(nation, greet);

        return ResponseEntity.accepted().body(greeting);
    }
}
