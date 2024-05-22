package com.example.Burger.controller;

import com.example.Burger.mapper.BurgerMapper;
import com.example.Burger.model.dto.BurgerDTO;
import com.example.Burger.model.entity.Burger;
import com.example.Burger.repository.BurgerRepository;
import com.example.Burger.service.BurgerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/burgers")
public class BurgerController {

    private final BurgerService burgerService;
    @Autowired
    private final BurgerMapper burgerMapper;
    @Autowired
    private final BurgerRepository burgerRepository;
    public BurgerController(BurgerService burgerService, BurgerMapper burgerMapper, BurgerRepository burgerRepository) {
        this.burgerService = burgerService;
        this.burgerMapper = burgerMapper;
        this.burgerRepository = burgerRepository;
    }

    @GetMapping
    public List<BurgerDTO> getAllBurgers() {
        return burgerService.getAllBurgers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Burger> getBurgerById(@PathVariable("id") Long id) {
        Optional<Burger> res =burgerService.getById(id);

        return res.isEmpty()
                ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT)
                : ResponseEntity.ok(res.get());
    }


    @PostMapping
    public ResponseEntity<BurgerDTO> createNewBurger(@Valid @RequestBody BurgerDTO burgerDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        BurgerDTO createdBurger = burgerService.createNewBurger(burgerDTO);
        return new ResponseEntity<>(createdBurger, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStore(@PathVariable Long id, @Valid @RequestBody BurgerDTO burgerDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

     burgerService.updateBurger(id, burgerDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<BurgerDTO> partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Optional<Burger> optionalBurger = burgerRepository.partialUpdate(id, updates);
        return optionalBurger.map(burger -> ResponseEntity.ok(burgerMapper.toBurgerDTO(burger)))
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public void deleteBurger(@PathVariable Long id) {
        burgerService.deleteBurger(id);
    }
}