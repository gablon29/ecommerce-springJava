package com.ecommercekine.tienda_cesar.service.product;

import com.ecommercekine.tienda_cesar.model.Product;

import java.util.List;

public interface IProductService {
    Product findById(Long id);
    Product save(Product product);
    void delete(Long id);
    void updateProduct(Long id, Product product);

    List<Product> findAll();
    List<Product> findByCategory(Long categoryId);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(Long categoryId, String brand);
    List<Product> getProductsByName(String name);
    Long countProductsByCategory(String brand, String name);
}
