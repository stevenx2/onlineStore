package com.johanx.tienda.dao;

import com.johanx.tienda.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * repositorio de los proveedores
 */
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
}
