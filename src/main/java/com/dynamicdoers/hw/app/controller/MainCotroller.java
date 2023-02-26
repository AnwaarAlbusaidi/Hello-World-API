package com.dynamicdoers.hw.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
public class MainCotroller {
    private ConcurrentMap<String, String> responses = new ConcurrentHashMap<>();

    @GetMapping(path = "/api")
    public ResponseEntity<HashMap<String, String>> giveQuote(@RequestParam(value = "name", defaultValue = "word") String name) {
        try {
            String requestId = UUID.randomUUID().toString();
            responses.put(requestId, "Hello " + name);
            HashMap<String, String> response = new HashMap<>();
            response.put("Result", "Hello " + name);
            response.put("Status", "200");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            HashMap<String, String> error = new HashMap<>();
            error.put("Message", "Error occurred while processing the request: " + e.getMessage());
            error.put("Status", "500");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    @GetMapping(path = "/api/hw")
    public String giveString()
    {
        return "Hello word";
    }

}
