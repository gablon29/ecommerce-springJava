package com.ecommercekine.tienda_cesar.dto;


import lombok.Data;

@Data
public class ImageDto {
    private Long imageId;
    private String imageName;
    private String downloadUrl;
}
