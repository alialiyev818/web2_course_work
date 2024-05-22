package com.example.Burger.service;

import com.example.Burger.mapper.BurgerMapper;
import com.example.Burger.model.dto.BurgerDTO;
import com.example.Burger.model.entity.Burger;
import com.example.Burger.repository.BurgerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BurgerServiceTest {

    @Mock
    private BurgerRepository burgerRepository;

    @Mock
    private BurgerMapper burgerMapper;

    @InjectMocks
    private BurgerService burgerService;

    public BurgerServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBurgers() {

        List<Burger> burgers = new ArrayList<>();
        Mockito.when(burgerRepository.findAll()).thenReturn(burgers);
        List<BurgerDTO> result = burgerService.getAllBurgers();
        assertEquals(0, result.size());
    }

    @Test
    public void testGetBurgerById() {
        Long id = 1L;
        Burger burger = new Burger();
        Mockito.when(burgerRepository.findById(id)).thenReturn(Optional.of(burger));
        Optional<Burger> result = burgerService.getById(id);
        assertTrue(result.isPresent());
        assertEquals(burger, result.get());
    }

    @Test
    public void testCreateNewBurger() {
        BurgerDTO burgerDTO = new BurgerDTO();
        Burger burger = new Burger();
        Mockito.when(burgerMapper.toBurger(burgerDTO)).thenReturn(burger);
        Mockito.when(burgerRepository.save(burger)).thenReturn(burger);
        Mockito.when(burgerMapper.toBurgerDTO(burger)).thenReturn(burgerDTO);
        BurgerDTO result = burgerService.createNewBurger(burgerDTO);
        assertEquals(burgerDTO, result);
    }
}
