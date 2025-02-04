package com.ecommercekine.tienda_cesar.service.category;

import com.ecommercekine.tienda_cesar.model.Category;

import java.util.List;

public interface ICategoryService {
    Category addCategory(Category category);
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    void deleteCategory(Long id);
    Category updateCategory(Category category, Long id);
}
