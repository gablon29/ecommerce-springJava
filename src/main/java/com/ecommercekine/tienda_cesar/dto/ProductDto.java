package com.ecommercekine.tienda_cesar.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String brand;
    private Double price;
    private Integer stock;
    private String description;
    private String category;
    private List<ImageDto> images;
}
