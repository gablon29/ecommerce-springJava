package com.ecommercekine.tienda_cesar.request;

import com.ecommercekine.tienda_cesar.model.Category;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddProductRequest {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int stock;
    private String description;
    private Category category;
}
