package com.johanx.tienda.services.impl;


import com.johanx.tienda.dao.CategoryRepository;
import com.johanx.tienda.model.Category;
import com.johanx.tienda.services.ICategory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * servicio de la clase de categorías
 */
@Service
@Transactional
public class CategoryService implements ICategory {


    @Autowired
    private CategoryRepository dao;

    @Override
    public List<Category> findAll() {
        return dao.findAll();
    }

    @Override
    public Category findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public Category save(Category category) {
        return dao.save(category);
    }

    @Override
    public boolean existsById(Long id) {
        return dao.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}
