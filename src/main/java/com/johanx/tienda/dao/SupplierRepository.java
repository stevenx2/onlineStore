package com.johanx.tienda.dao;

import com.johanx.tienda.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * repositorio de los proveedores
 */
public interface SupplierRepository extends JpaRepository<Supplier,Long> {

    Optional<Supplier> findByName(String name);
}
