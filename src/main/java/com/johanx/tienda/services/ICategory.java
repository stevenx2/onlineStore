package com.johanx.tienda.services;

import com.johanx.tienda.model.Category;

import java.util.List;

/**
 * firma para las clases que quieran implementar el servicio de las categorías
 */
public interface ICategory {

    List<Category> findAll();

    Category findById(Long id);

    Category save(Category category);

    boolean existsById(Long id);

    void deleteById(Long id);

}
