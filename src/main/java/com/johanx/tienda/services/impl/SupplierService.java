package com.johanx.tienda.services.impl;

import com.johanx.tienda.dao.SupplierRepository;
import com.johanx.tienda.model.Supplier;
import com.johanx.tienda.services.ISupplier;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class SupplierService implements ISupplier {


    @Autowired
    private SupplierRepository dao;

    @Override
    public List<Supplier> findAll() {
        return dao.findAll();
    }

    @Override
    public Supplier findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(Long id) {
        return dao.existsById(id);
    }

    @Override
    public Supplier save(Supplier supplier) {
        return dao.save(supplier);
    }

    @Override
    public Supplier findByName(String name) {
        return dao.findByName(name).orElse(null);
    }

    @Override
    public boolean existsByName(String name) {
        return dao.findByName(name).isPresent();
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

}
