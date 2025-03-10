package com.ecommercekine.tienda_cesar.repository;

import com.ecommercekine.tienda_cesar.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryName(String category);

    List<Product> findByBrand(String brand);

    List<Product> findByCategoryNameAndBrand(String category, String brand);

    List<Product> findByName(String name);

    Long countByBrandAndName(String brand, String name);

    List<Product> findProductsByBrandAndName(String brand, String name);
}
