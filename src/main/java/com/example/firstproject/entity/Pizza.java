package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private int price;

    public void patch(Pizza pizza) {
        if(pizza.name != null)
            this.name = pizza.name;
        if(pizza.price != 0)
            this.price = pizza.price;
    }
}
