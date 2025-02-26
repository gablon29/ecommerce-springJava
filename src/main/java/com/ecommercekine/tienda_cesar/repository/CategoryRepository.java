package com.ecommercekine.tienda_cesar.repository;

import com.ecommercekine.tienda_cesar.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);

    boolean existByName(String name);
}
