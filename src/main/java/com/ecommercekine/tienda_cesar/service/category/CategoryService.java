package com.ecommercekine.tienda_cesar.service.category;

import com.ecommercekine.tienda_cesar.exceptions.AlreadyExistException;
import com.ecommercekine.tienda_cesar.exceptions.CategoryNotFoundException;
import com.ecommercekine.tienda_cesar.model.Category;
import com.ecommercekine.tienda_cesar.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category).filter(category1 -> !categoryRepository.existByName(category1.getName())).
                map(categoryRepository::save)
                .orElseThrow(() -> new AlreadyExistException("Category already exists!"));
    }

    @Override
    public List<Category> getAllCategories() {
        return List.of();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException("Category not found!")
        );
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id).ifPresentOrElse(categoryRepository::delete,
                () -> {
                    throw new CategoryNotFoundException("Category not found!");
                });

    }

    @Override
    public Category updateCategory(Category category, Long id) {
        return Optional.ofNullable(getCategoryById(id)).map(
                category1 -> {
                    category1.setName(category.getName());
                    return categoryRepository.save(category1);
                }) .orElseThrow(() -> new CategoryNotFoundException("Category not found!"));
    }
}
