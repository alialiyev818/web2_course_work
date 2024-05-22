package com.example.Burger.controller;

import com.example.Burger.model.dto.BurgerDTO;
import com.example.Burger.model.entity.Burger;
import com.example.Burger.service.BurgerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BurgerControllerTest {

    @Mock
    private BurgerService burgerService;

    @InjectMocks
    private BurgerController burgerController;

    public BurgerControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBurgers() {

        List<BurgerDTO> burgers = new ArrayList<>();
        Mockito.when(burgerService.getAllBurgers()).thenReturn(burgers);
        List<BurgerDTO> result = burgerController.getAllBurgers();
        assertEquals(burgers.size(), result.size());
    }

    @Test
    public void testGetBurgerById() {
        Long id = 1L;
        Burger burger = new Burger();
        Mockito.when(burgerService.getById(id)).thenReturn(Optional.of(burger));
        ResponseEntity<Burger> result = burgerController.getBurgerById(id);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void testCreateNewBurger() {
        BurgerDTO burgerDTO = new BurgerDTO();
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(burgerService.createNewBurger(burgerDTO)).thenReturn(burgerDTO);
        ResponseEntity<BurgerDTO> result = burgerController.createNewBurger(burgerDTO, bindingResult);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
    }
}
