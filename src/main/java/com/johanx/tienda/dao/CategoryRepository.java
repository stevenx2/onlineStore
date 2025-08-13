package com.johanx.tienda.dao;

import com.johanx.tienda.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * repositorio de las categorías
 */
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByName(String name);
}
