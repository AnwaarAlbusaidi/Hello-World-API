package com.dynamicdoers.hw.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/**
 This class represents the main controller for a RESTful API application.
 It handles GET, POST, PUT and DELETE requests, and returns appropriate responses.
 */
@RestController
@RequestMapping(path ="/api")
public class MainCotroller {
    // A map to store responses for each request
    private ConcurrentMap<String, String> responses = new ConcurrentHashMap<>();

    /**
     * Handles a GET request and returns a response containing a greeting message.
     * @param name The name to greet.
     * @return A ResponseEntity containing the response message and HTTP status code.
     */
    @GetMapping
    public ResponseEntity<HashMap<String, String>> giveQuote(@RequestParam(value = "name", defaultValue = "word") String name) {
        try {
            // Generate a unique ID for the request
            String requestId = UUID.randomUUID().toString();
            // Add the response to the map
            responses.put(requestId, "Hello " + name);
            // Create a response map
            HashMap<String, String> response = new HashMap<>();
            response.put("Result", "Hello " + name);
            response.put("Status", "200");
            // Return a ResponseEntity with the response and HTTP status code
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            // If an exception is thrown, return an error message with HTTP status code 500
            HashMap<String, String> error = new HashMap<>();
            error.put("Message", "Error occurred while processing the request: " + e.getMessage());
            error.put("Status", "500");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    /**
     * Handles a GET request and returns a fixed string.
     * @return A string containing a fixed message.
     */
    @GetMapping(path = "api/hw")
    public String giveString()
    {
        return "Hello word";
    }

    /**
     * Handles a POST request and returns a fixed string.
     * @return A string containing a fixed message.
     */
    @PostMapping
    public String givePostRequest()
    {
        return "This is POST request";
    }

    /**
     * Handles a PUT request and returns a fixed string.
     * @return A string containing a fixed message.
     */
    @PutMapping
    public String givePutRequest()
    {
        return "This is PUT request";
    }

    /**
     * Handles a DELETE request and returns a fixed string.
     * @return A string containing a fixed message.
     */
    @DeleteMapping
    public String giveDeleteRequest()
    {
        return "This is DELETE request";
    }

}
