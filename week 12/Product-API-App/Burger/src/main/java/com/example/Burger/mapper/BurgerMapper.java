package com.example.Burger.mapper;

import com.example.Burger.model.dto.BurgerDTO;
import com.example.Burger.model.entity.Burger;

import org.springframework.stereotype.Component;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;


@Component
@Mapper(componentModel = "spring")
public interface BurgerMapper {
    BurgerDTO toBurgerDTO(Burger burger);

    Burger toBurger(BurgerDTO burgerDTO);

    default List<BurgerDTO> toBurgerDTOList(List<Burger> burgers) {
        return burgers.stream()
                .map(this::toBurgerDTO)
                .collect(Collectors.toList());
    }

    default BurgerDTO toBurgerDTOOrNull(Burger burger) {
        return burger == null ? null : toBurgerDTO(burger);
    }
}