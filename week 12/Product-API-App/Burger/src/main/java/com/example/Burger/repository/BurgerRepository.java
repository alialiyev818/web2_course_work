package com.example.Burger.repository;

import com.example.Burger.model.entity.Burger;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@Repository
public interface BurgerRepository extends JpaRepository<Burger, Long> {
    @Transactional
    default Optional<Burger> partialUpdate(Long id, Map<String, Object> updates) {
        Optional<Burger> optionalBurger = findById(id);
        optionalBurger.ifPresent(burger -> {
            updates.forEach((fieldName, value) -> {
                try {
                    Field field = ReflectionUtils.findField(Burger.class, fieldName);
                    field.setAccessible(true);
                    field.set(burger, value);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        });
        return optionalBurger;
    }
}
