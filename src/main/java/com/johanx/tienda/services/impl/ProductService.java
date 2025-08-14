package com.johanx.tienda.services.impl;


import com.johanx.tienda.dao.CategoryRepository;
import com.johanx.tienda.dao.ProductRepository;
import com.johanx.tienda.dao.SupplierRepository;
import com.johanx.tienda.model.Category;
import com.johanx.tienda.model.Product;
import com.johanx.tienda.model.Supplier;
import com.johanx.tienda.runtimeException.ResourceNotFoundException;
import com.johanx.tienda.services.IProduct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Autowired
    private SupplierRepository supplierDao;

    @Autowired
    private CategoryRepository categoryDao;



    @Override
    public List<Product> findAll() {
        return dao.findAll();
    }

    @Override
    public Product findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    /**
     * guarda un nuevo producto
     * @param product el objeto a insertar
     * @return el nuevo producto
     * @throws ResourceNotFoundException lanzada cuando el id del supplier o category no existe en la base de datos
     * @throws  DataIntegrityViolationException si el nombre del producto ya existe
     */
    @Override
    public Product save(Product product) throws ResourceNotFoundException, DataIntegrityViolationException {

        //si ya existe un producto con eso nombre entonces de lanza una excepción
        if (existsByName(product.getName())) {
            throw new DataIntegrityViolationException("No es posible guardar el producto. El nombre '" + product.getName() +
                    "' ya está registrado en otro producto o coincide con el nombre anterior si se está editando.");
        }


        Long categoryId = product.getCategory().getCategory_id();
        Long supplierId = product.getSupplier().getSupplier_id();

        if(!categoryDao.existsById(categoryId)){
            throw new ResourceNotFoundException("el id " + categoryId + " no esta relacionado con ninguna categoría, Favor especificar un identificador existente");
        }

        if(!supplierDao.existsById(supplierId)){
            throw new ResourceNotFoundException("el id " + supplierId + " no esta relacionado con ningún proveedor, Favor especificar un identificador existente");
        }

        //se obtiene la categoria y proveedor de la base de datos
        Category categoryOnDb = categoryDao.findById(categoryId).orElse(null);
        Supplier supplierOnDb = supplierDao.findById(supplierId).orElse(null);

        product.setCategory(categoryOnDb);
        product.setSupplier(supplierOnDb);

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
