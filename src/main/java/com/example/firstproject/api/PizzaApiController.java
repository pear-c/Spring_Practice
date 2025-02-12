package com.example.firstproject.api;

import com.example.firstproject.dto.PizzaDto;
import com.example.firstproject.service.CommentService;
import com.example.firstproject.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PizzaApiController {
    @Autowired
    private PizzaService pizzaService;
    @Autowired
    private CommentService commentService;

    // 조회 (단건)
    @GetMapping("/api/pizza/{id}")
    public ResponseEntity<PizzaDto> showOne(@PathVariable Long id) {
        PizzaDto pizzaDto = pizzaService.showOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(pizzaDto);
    }

    // 조회 (목록)
    @GetMapping("/api/pizza")
    public ResponseEntity<List<PizzaDto>> showAll() {
        List<PizzaDto> dtos = pizzaService.showAll();

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 생성
    @PostMapping("/api/pizza")
    public ResponseEntity<PizzaDto> create(@RequestBody PizzaDto pizzaDto) {
        PizzaDto createdDto = pizzaService.create(pizzaDto);
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    // 수정
    @PatchMapping("/api/pizza/{id}")
    public ResponseEntity<PizzaDto> update(@PathVariable Long id, @RequestBody PizzaDto pizzaDto) {
        PizzaDto updatedDto = pizzaService.update(id, pizzaDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    // 삭제
    @DeleteMapping("/api/pizza/{id}")
    public ResponseEntity<PizzaDto> delete(@PathVariable Long id) {
        PizzaDto deletedDto = pizzaService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }

}
