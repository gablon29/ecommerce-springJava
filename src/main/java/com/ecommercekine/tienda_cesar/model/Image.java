package com.ecommercekine.tienda_cesar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;

    // Para guardar la imagen en la base de datos
    @Lob
    private Blob data;
    private String downloadUri;

    // Relacion de uno a muchos
    @ManyToOne
    // Generamos la llave foranea
    @JoinColumn(name = "product_id")
    private Product product;
}
