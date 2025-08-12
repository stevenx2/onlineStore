package com.johanx.tienda.Controller;

import com.johanx.tienda.model.Supplier;
import com.johanx.tienda.runtimeException.ResourceNotFoundException;
import com.johanx.tienda.services.ISupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/suppliers")
public class SupplierController {

    @Autowired
    private ISupplier supplierService;


    @GetMapping
    public ResponseEntity<List<Supplier>> getAll(){
        return ResponseEntity.ok(supplierService.findAll());
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Supplier> getById(@PathVariable Long id){
        Supplier supplierFound = supplierService.findById(id);

        if(supplierFound != null){
            return ResponseEntity.ok(supplierFound);
        } else throw new ResourceNotFoundException("no existe un proveedor con id " + id);

    }


    @PostMapping
    public ResponseEntity<Supplier> postSupplier(@RequestBody Supplier supplier){
        Supplier savedSupplier = supplierService.save(supplier);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedSupplier.getSupplier_id()).toUri();
        return ResponseEntity.created(uri).body(savedSupplier);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<Supplier> putSupplier(@PathVariable Long id, @RequestBody Supplier supplier){
        if(!supplierService.existsById(id)){
            throw new ResourceNotFoundException("no existe un proveedor con id " + id);
        }

        supplier.setSupplier_id(id);
        Supplier updatedSupplier = supplierService.save(supplier);
        return ResponseEntity.ok(updatedSupplier);
    }
}
