package com.example.proxy.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/proxy/burgers")
public class BurgerProxyController {

    private static final Logger logger = LoggerFactory.getLogger(BurgerProxyController.class);
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "http://localhost:8080/burgers";

    @GetMapping
    public ResponseEntity<String> getAllBurgers() {
        logger.info("Fetching all burgers");
        String response = restTemplate.getForObject(BASE_URL, String.class);
        logger.info("Burgers' List: {}", response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getBurgerById(@PathVariable Long id) {
        logger.info("Fetching burger with id: {}", id);
        String response = restTemplate.getForObject(BASE_URL + "/" + id, String.class);
        logger.info("Burger: {}", response);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<String> createNewBurger(@RequestBody Map<String, Object> burgerDTO) {
        logger.info("Creating new burger: {}", burgerDTO);
        ResponseEntity<String> response = restTemplate.postForEntity(BASE_URL, burgerDTO, String.class);
        logger.info("Created Burger: {}", response);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBurger(@PathVariable Long id, @RequestBody Map<String, Object> burgerDTO) {
        logger.info("Updating burger with id: {}", id);
        restTemplate.put(BASE_URL + "/" + id, burgerDTO);
        logger.info("Burger updated");
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        logger.info("Partially updating burger with id: {}", id);
        restTemplate.patchForObject(BASE_URL + "/" + id, updates, String.class);
        logger.info("Partial update completed");
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBurger(@PathVariable Long id) {
        logger.info("Deleting burger with id: {}", id);
        restTemplate.delete(BASE_URL + "/" + id);
        logger.info("Burger deleted");
        return ResponseEntity.noContent().build();
    }
}
