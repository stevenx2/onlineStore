package com.johanx.tienda.services;

import com.johanx.tienda.model.Product;

import java.util.List;

/**
 * firma de métodos para el servicio de productos
 */
public interface IProduct {

    List<Product> findAll();

    Product findById(Long id);

    Product save(Product product);

    void deleteById(Long id);

    Product findByName(String name);

    boolean existsById(Long id);

    boolean existsByName(String name);
}
