package com.ecommercekine.tienda_cesar.service.product;

import com.ecommercekine.tienda_cesar.dto.ProductDto;
import com.ecommercekine.tienda_cesar.exceptions.ProductNotFoundException;
import com.ecommercekine.tienda_cesar.model.Category;
import com.ecommercekine.tienda_cesar.model.Product;
import com.ecommercekine.tienda_cesar.repository.CategoryRepository;
import com.ecommercekine.tienda_cesar.repository.ProductRepository;
import com.ecommercekine.tienda_cesar.request.AddProductRequest;
import com.ecommercekine.tienda_cesar.request.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Override
    public Product save(AddProductRequest request) {
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> {
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
            request.setCategory(category);
            return productRepository.save(create(request, category));
    }

    public Product create(AddProductRequest request, Category category) {
       return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getStock(),
                request.getDescription(),
                category
       );
    }

    @Override
    public void delete(Long id) {
        productRepository.findById(id).ifPresentOrElse(productRepository::delete, () -> {
            throw new ProductNotFoundException("Product not found");
        });

    }

    @Override
    public Product updateProduct(ProductUpdateRequest request, Long id) {
        return productRepository.findById(id)
                .map(existingProduct -> updateExistingProduct(existingProduct, request))
                .map(productRepository::save)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    public Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request) {
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setStock(request.getStock());
        existingProduct.setDescription(request.getDescription());
        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);
        return existingProduct;
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
        return productRepository.findByName(name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }
    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findProductsByBrandAndName(brand, name);
    }

    @Override
    public List<ProductDto> getConvertProducts(List<Product> products) {
        return products.stream().map(this::convertProduct).toList();
    }

    @Override
    public ProductDto convertProduct(Product product) {
        return null;
    }
}
