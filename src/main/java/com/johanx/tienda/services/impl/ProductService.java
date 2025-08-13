package com.johanx.tienda.services.impl;


import com.johanx.tienda.dao.ProductRepository;
import com.johanx.tienda.model.Product;
import com.johanx.tienda.services.IProduct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * servicio de los productos
 */
@Service
@Transactional
public class ProductService implements IProduct {

    @Autowired
    private ProductRepository dao;

    @Override
    public List<Product> findAll() {
        return dao.findAll();
    }

    @Override
    public Product findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return dao.save(product);
    }

    @Override
    public void deleteById(Long id) {
       dao.deleteById(id);
    }

    @Override
    public Product findByName(String name) {
        return dao.findByName(name).orElse(null);
    }

    @Override
    public boolean existsById(Long id) {
        return dao.existsById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return dao.findByName(name).isPresent();
    }
}
