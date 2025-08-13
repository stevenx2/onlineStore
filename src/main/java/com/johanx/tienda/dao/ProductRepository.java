package com.johanx.tienda.dao;

import com.johanx.tienda.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * repositorio de los productos
 */
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByName(String name);
}
