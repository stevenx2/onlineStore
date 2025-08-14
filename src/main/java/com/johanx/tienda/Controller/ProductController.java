package com.johanx.tienda.Controller;

import com.johanx.tienda.model.Product;
import com.johanx.tienda.runtimeException.ResourceNotFoundException;
import com.johanx.tienda.services.ICategory;
import com.johanx.tienda.services.IProduct;
import com.johanx.tienda.services.ISupplier;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Product> getByName(@PathVariable String name) {
        if (!productService.existsByName(name)) {
            throw new ResourceNotFoundException("No existe un producto con el nombre '" + name + "'");
        }

        return ResponseEntity
                .ok(productService.findByName(name));
    }



    /**
     * guardar un nuevo producto.En el json puedes especificar los objetos supplier y category completos o solo su id
     */
    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
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

        product.setProduct_id(id);

        return ResponseEntity
                .ok(productService.save(product));
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {

        if (!productService.existsById(id)) {
            throw new ResourceNotFoundException("El producto que quiere eliminar con el id " + id + " no existe");
        }


        productService.deleteById(id);

        return ResponseEntity
                .noContent().build();
    }


}
