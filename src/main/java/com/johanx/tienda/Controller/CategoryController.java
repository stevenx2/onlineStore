package com.johanx.tienda.Controller;


import com.johanx.tienda.model.Category;
import com.johanx.tienda.runtimeException.ResourceNotFoundException;
import com.johanx.tienda.services.ICategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * restController de las categorias
 */
@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private ICategory categoryService;


    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        Category categoryFound = categoryService.findById(id);
        if (categoryFound != null) {
            return ResponseEntity.ok(categoryFound);
        } else throw new ResourceNotFoundException("La categoría con id " + id + " no existe");
    }



    @PostMapping
    public ResponseEntity<Category> postSupplier(@RequestBody Category category){

        if(categoryService.existsByName(category.getName())){
            throw new DataIntegrityViolationException("No pueden existir dos categorías con el mismo nombre");
        }

        Category savedCategory = categoryService.save(category);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedCategory.getCategory_id()).toUri();
        return ResponseEntity.created(uri).body(savedCategory);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> putCategory(@PathVariable Long id,@RequestBody Category category){
        if(!categoryService.existsById(id)){
            throw new ResourceNotFoundException("La categoría con id " + id + " no existe");
        }

        category.setCategory_id(id);
        Category updatedCategory = categoryService.save(category);
        return ResponseEntity.ok(updatedCategory);
    }


}
