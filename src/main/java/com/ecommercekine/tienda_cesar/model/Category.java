package com.ecommercekine.tienda_cesar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    // generamos la relacion de uno a muchos 1:N
    // mappedBy es el nombre del atributo en la clase Product
    @OneToMany(mappedBy = "category")
    private List<Product>products;

    public Category(String name) {
        this.name = name;
    }
}
