package com.johanx.tienda.Controller;

import com.johanx.tienda.model.Category;
import com.johanx.tienda.model.Product;
import com.johanx.tienda.model.Supplier;
import com.johanx.tienda.runtimeException.ResourceNotFoundException;
import com.johanx.tienda.services.ICategory;
import com.johanx.tienda.services.IProduct;
import com.johanx.tienda.services.ISupplier;
import com.johanx.tienda.services.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * contralador de los productos
 */
@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private IProduct productService;

    @Autowired
    private ICategory categoryService;

    @Autowired
    private ISupplier supplierService;


    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.findAll());
    }


    @GetMapping(value = "id/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        if (!productService.existsById(id)) {
            throw new ResourceNotFoundException("No existe un producto con id " + id);
        }

        return ResponseEntity
                .ok(productService.findById(id));
    }


    @GetMapping(value = "name/{name}")
    public ResponseEntity<Product> getByName(@PathVariable String name){
        if(!productService.existsByName(name)){
            throw new ResourceNotFoundException("No existe un producto con el nombre '" + name +"'");
        }

        return ResponseEntity
                .ok(productService.findByName(name));
    }




    /**
     * guardar un nuevo producto. Tienes que espeficar exactamente el objeto de tipo Supplier y Category
     */
    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {

        //se obtiene la categoria y proveedor de la base de datos
        Category categoryOnDb = categoryService.findById(product.getCategory().getCategory_id());
        Supplier supplierOnDb = supplierService.findById(product.getSupplier().getSupplier_id());


        //verifica que no viole la integridad de la base de datos
        checkProductIntegrity(product);


        product.setCategory(categoryOnDb);
        product.setSupplier(supplierOnDb);


        Product productSaved = productService.save(product);

        //url para acceder al recurso creado. se guarda en el head
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(productSaved.getProduct_id()).toUri();

        return ResponseEntity
                .created(uri).
                body(productSaved);


    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {


        if (!productService.existsById(id)) {
            throw new ResourceNotFoundException("El producto que quiere actualizar con el id " + id + " no existe");
        }

        //verifica que no viole la integridad de la base de datos
        checkProductIntegrity(product);

        product.setProduct_id(id);


        return ResponseEntity
                .ok(productService.save(product));
    }



    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){

        if (!productService.existsById(id)) {
            throw new ResourceNotFoundException("El producto que quiere eliminar con el id " + id + " no existe");
        }


        productService.deleteById(id);

        return ResponseEntity
                .noContent().build();
    }


    /**
     * verifica que el producto se puede insertar o actualizar en la base de datos.
     * Algunos de sus parámetros para no tomar el producto como apto es que tenga un nombre
     * de un producto existente. Lanza excepción es caso de que viole la integridad de la base de datos
     */
    private void checkProductIntegrity(Product product) throws DataIntegrityViolationException {
        //se obtiene la categoria y proveedor de la base de datos
        Category categoryOnDb = categoryService.findById(product.getCategory().getCategory_id());
        Supplier supplierOnDb = supplierService.findById(product.getSupplier().getSupplier_id());


        //si ya existe un producto con eso nombre entonces de lanza una excepción
        if (productService.existsByName(product.getName())) {
            throw new DataIntegrityViolationException("No es posible guardar el producto. El nombre '" + product.getName() +
                    "' ya está registrado en otro producto o coincide con el nombre anterior si se está editando.");
        }

        //si la categoria no existe en la base de datos se lanza una excepción
        if (!product.getCategory().equals(categoryOnDb)) {
            throw new ResourceNotFoundException("La categoría que le quiere asignar al producto no existe");
        }

        //lanzar excepción si no existe el proveedor en la base de datos
        if (!product.getSupplier().equals(supplierOnDb)) {
            throw new ResourceNotFoundException("El proveedor que le quiere asignar al producto no existe");
        }

    }


}
