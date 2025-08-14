package com.johanx.tienda.services;

import com.johanx.tienda.model.Product;
import com.johanx.tienda.runtimeException.ResourceNotFoundException;

import java.util.List;

/**
 * firma de métodos para el servicio de productos
 */
public interface IProduct {

    List<Product> findAll();

    Product findById(Long id);

    Product save(Product product) throws ResourceNotFoundException;

    void deleteById(Long id);

    Product findByName(String name);

    boolean existsById(Long id);

    boolean existsByName(String name);
}
