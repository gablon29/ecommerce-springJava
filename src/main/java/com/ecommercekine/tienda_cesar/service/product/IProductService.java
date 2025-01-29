package com.ecommercekine.tienda_cesar.service.product;

import com.ecommercekine.tienda_cesar.model.Product;
import com.ecommercekine.tienda_cesar.request.AddProductRequest;

import java.util.List;

public interface IProductService {
    Product findById(Long id);
    Product save(AddProductRequest product);
    void delete(Long id);
    void updateProduct(Long id, Product product);

    List<Product> findAll();
    List<Product> findByCategoryName(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    Long countProductsByBrandAndName(String brand, String name);
}
