package com.example.Burger.service;

import com.example.Burger.mapper.BurgerMapper;
import com.example.Burger.model.dto.BurgerDTO;
import com.example.Burger.model.entity.Burger;
import com.example.Burger.repository.BurgerRepository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.stream.Collectors;

@Service
public class BurgerService {

    private final BurgerRepository burgerRepository;
    private final BurgerMapper burgerMapper;
    private static final Logger LOG = LoggerFactory.getLogger(BurgerService.class);

    public BurgerService(BurgerRepository burgerRepository, BurgerMapper burgerMapper) {
        this.burgerRepository = burgerRepository;
        this.burgerMapper = burgerMapper;
    }

    public List<BurgerDTO> getAllBurgers() {
        LOG.info("Getting all burgers");
        long startTime = System.currentTimeMillis();
        List<Burger> burgers = burgerRepository.findAll();
        long endTime = System.currentTimeMillis();
        LOG.info("Finished getting all burgers. Took " + (endTime - startTime) + " ms.");
        return burgerMapper.toBurgerDTOList(burgers);
    }

    public Optional<Burger> getById(Long id) {
        LOG.info("Getting burger by ID: " + id);
        long startTime = System.currentTimeMillis();
        Optional<Burger> burger = burgerRepository.findById(id);
        long endTime = System.currentTimeMillis();
        LOG.info("Finished getting burger by ID: " + id + ". Took " + (endTime - startTime) + " ms.");
        return burger;
    }

    public BurgerDTO createNewBurger(BurgerDTO burgerDTO) {
        LOG.info("Creating new store: " + burgerDTO);
        long startTime = System.currentTimeMillis();
        Burger burger = burgerMapper.toBurger(burgerDTO);
        burger = burgerRepository.save(burger);
        long endTime = System.currentTimeMillis();
        LOG.info("Finished creating new burger. Took " + (endTime - startTime) + " ms.");
        return burgerMapper.toBurgerDTO(burger);
    }

    public void updateBurger(Long id, BurgerDTO burgerDTO) {
        LOG.info("Updating burger with ID: " + id);
        long startTime = System.currentTimeMillis();
        Burger burger = burgerMapper.toBurger(burgerDTO);
        burger.setId(id);
        burgerRepository.save(burger);
        long endTime = System.currentTimeMillis();
        LOG.info("Finished updating burger with ID: " + id + ". Took " + (endTime - startTime) + " ms.");
    }

    public Optional<Burger> partialUpdate(Long id, Map<String, Object> map) {
        LOG.info("Partially updating burger with ID: " + id + " with new data: " + map);
        long startTime = System.currentTimeMillis();
        Optional<Burger> oldBurger = burgerRepository.findById(id);

        if (oldBurger.isEmpty()) {
            LOG.warn("Burger with ID: " + id + " does not exist.");
            return oldBurger;
        }

        Burger br = oldBurger.get();
        map.forEach((fieldName, value) -> {
            try {
                Field field = ReflectionUtils.findField(Burger.class, fieldName);
                field.setAccessible(true);
                field.set(br, value);
            } catch (Exception e) {
                LOG.error(e.getMessage());
            }
        });

        Burger updatedBurger = burgerRepository.save(br);
        long endTime = System.currentTimeMillis();
        LOG.info("Finished partially updating burger with ID: " + id + ". Took " + (endTime - startTime) + " ms.");
        return Optional.of(updatedBurger);
    }

    public void deleteBurger(Long id) {
        LOG.info("Deleting burger with ID: " + id);
        long startTime = System.currentTimeMillis();
        burgerRepository.deleteById(id);
        long endTime = System.currentTimeMillis();
        LOG.info("Finished deleting burger with ID: " + id + ". Took " + (endTime - startTime) + " ms.");
    }
}
