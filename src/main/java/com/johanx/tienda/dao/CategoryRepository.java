package com.johanx.tienda.dao;

import com.johanx.tienda.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * repositorio de las categorías
 */
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
