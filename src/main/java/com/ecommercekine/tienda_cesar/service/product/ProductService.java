package com.ecommercekine.tienda_cesar.service.product;

import com.ecommercekine.tienda_cesar.exceptions.ProductNotFoundException;
import com.ecommercekine.tienda_cesar.model.Product;
import com.ecommercekine.tienda_cesar.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{

    private ProductRepository productRepository;


    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.findById(id).ifPresentOrElse(productRepository::delete, () -> {
            throw new ProductNotFoundException("Product not found");
        });

    }

    @Override
    public void updateProduct(Long id, Product product) {
        productRepository.findById(id).ifPresentOrElse(p -> {
            product.setId(p.getId());
            productRepository.save(product);
        }, () -> {
            throw new ProductNotFoundException("Product not found");
        });
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByCategoryName(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return List.of();
    }

    @Override
    public Long countProductsByCategory(String brand, String name) {
        return 0L;
    }
}
