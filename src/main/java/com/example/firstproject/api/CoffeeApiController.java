package com.example.firstproject.api;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CoffeeApiController {
    @Autowired
    private CoffeeRepository coffeeRepository;

    // GET
    @GetMapping("/api/coffees")
    public List<Coffee> index() {
        return coffeeRepository.findAll();
    }

    @GetMapping("/api/coffees/{id}")
    public Coffee show(@PathVariable Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    // POST
    @PostMapping("/api/coffees/{id}")
    public Coffee create(@RequestBody CoffeeDto dto) {
        Coffee coffee = dto.toEntity();
        return coffeeRepository.save(coffee);
    }

    // PATCH
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeDto dto) {
        Coffee coffee = dto.toEntity();
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if(target == null || id != coffee.getId()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        target.patch(coffee);
        Coffee updated = coffeeRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // DELETE
    @DeleteMapping("api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id) {
        Coffee coffee = coffeeRepository.findById(id).orElse(null);
        if(coffee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        coffeeRepository.delete(coffee);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
