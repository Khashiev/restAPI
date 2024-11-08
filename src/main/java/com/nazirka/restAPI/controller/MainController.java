package com.nazirka.restAPI.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nazirka.restAPI.entity.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/api/main")
    public String mainListener() {
        return "Hello World";
    }

    @GetMapping("/api/cat")
    public String giveCat() {
        Cat cat = new Cat("Barsik", 5, 10);
        String json = null;

        try {
            json = objectMapper.writeValueAsString(cat);
        } catch (JsonProcessingException e) {
            System.out.println("Json Exception");
        }

        return json;
    }

    @PostMapping("/api/special")
    public String giveSpecialCat(@RequestParam String name) {
        Cat cat = new Cat(name, 5, 10);
        String json = null;

        try {
            json = objectMapper.writeValueAsString(cat);
        } catch (JsonProcessingException e) {
            System.out.println("Json Exception");
        }

        return json;
    }
}
