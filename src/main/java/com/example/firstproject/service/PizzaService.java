package com.example.firstproject.service;

import com.example.firstproject.dto.PizzaDto;
import com.example.firstproject.entity.Pizza;
import com.example.firstproject.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    public PizzaDto showOne(Long id) {
        Pizza pizza = pizzaRepository.findById(id).orElse(null);

        PizzaDto pizzaDto = PizzaDto.createPizzaDto(pizza);

        return pizzaDto;
    }

    public List<PizzaDto> showAll() {
        List<Pizza> pizzaList = pizzaRepository.findAll();

        List<PizzaDto> pizzaDtoList = new ArrayList<>();
        for(Pizza pizza : pizzaList) {
            PizzaDto pizzaDto = PizzaDto.createPizzaDto(pizza);
            pizzaDtoList.add(pizzaDto);
        }

        return pizzaDtoList;
    }

    public PizzaDto create(PizzaDto pizzaDto) {
        Pizza pizza = pizzaDto.toEntity();
        Pizza savedPizza = pizzaRepository.save(pizza);
        return PizzaDto.createPizzaDto(savedPizza);
    }

    public PizzaDto update(Long id, PizzaDto pizzaDto) {
        Pizza pizza = pizzaDto.toEntity();

        Pizza target = pizzaRepository.findById(id).orElse(null);
        if(target == null || id != pizza.getId()) {
            return null;
        }

        target.patch(pizza);
        Pizza savedPizza = pizzaRepository.save(target);
        return PizzaDto.createPizzaDto(savedPizza);
    }

    public PizzaDto delete(Long id) {
        Pizza pizza = pizzaRepository.findById(id).orElse(null);

        if(pizza == null) {
            return null;
        }
        pizzaRepository.delete(pizza);

        return PizzaDto.createPizzaDto(pizza);
    }
}
