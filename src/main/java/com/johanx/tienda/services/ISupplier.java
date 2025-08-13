package com.johanx.tienda.services;

import com.johanx.tienda.model.Supplier;

import java.util.List;

public interface ISupplier {

    List<Supplier> findAll();

    Supplier findById(Long id);

    boolean existsById(Long id);

    Supplier save(Supplier supplier);


    Supplier findByName(String name);

    boolean existsByName(String name);

    void deleteById(Long id);

}
