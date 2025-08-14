package com.johanx.tienda.Controller;


import com.johanx.tienda.model.DeliveryPerson;
import com.johanx.tienda.runtimeException.ResourceNotFoundException;
import com.johanx.tienda.services.IDeliveryPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * controlador con los endPoints para acceder a datos de los repartidores
 */
@RestController
@RequestMapping(value = "/deliverers")
public class DeliveryPersonController {

    @Autowired
    private IDeliveryPerson deliveryPersonService;


    /**
     * obtener todos los repartidores
     */
    @GetMapping
    public ResponseEntity<List<DeliveryPerson>> getAll(){
        return ResponseEntity.ok(deliveryPersonService.findAll());
    }


    /**
     * obtener repartidor por id
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<DeliveryPerson> getById(@PathVariable Long id){

        if(!deliveryPersonService.existsById(id)){
            throw new ResourceNotFoundException("No existe un repartidor con id " + id);
        }

        return ResponseEntity
                .ok(deliveryPersonService.findById(id));
    }


    /**
     * guardar un nuevo repartidor. Si el objeto ya existe entonces no se guarda
     */
    @PostMapping
    public ResponseEntity<DeliveryPerson> saveDeliveryPerson(@RequestBody DeliveryPerson deliveryPerson){
        boolean itAlreadyExists = deliveryPersonService.existsByNameLastNameAgeAndNationality(deliveryPerson);

        if(itAlreadyExists){
            throw new DataIntegrityViolationException("Ya existe un repartidor con las mismas credenciales");
        }


        DeliveryPerson savedDeliveryPerson = deliveryPersonService.save(deliveryPerson);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedDeliveryPerson.getDelivery_person_id()).toUri();

        return ResponseEntity
                .created(uri)
                .body(savedDeliveryPerson);

    }




    @PutMapping(value = "/{id}")
    public ResponseEntity<DeliveryPerson> updateDeliveryPerson(@PathVariable Long id, @RequestBody DeliveryPerson deliveryPerson){
        boolean itAlreadyExists = deliveryPersonService.existsByNameLastNameAgeAndNationality(deliveryPerson);

        if(itAlreadyExists){
            throw new DataIntegrityViolationException("Ya existe un repartidor con las mismas credenciales");
        }

        if(!deliveryPersonService.existsById(id)){
            throw new ResourceNotFoundException("El repartidor que quiere actualizar con id " + id + " no existe");
        }

        deliveryPerson.setDelivery_person_id(id);

        DeliveryPerson updatedDeliveryPerson = deliveryPersonService.save(deliveryPerson);

        return ResponseEntity
                .ok(updatedDeliveryPerson);
    }



    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteDeliveryPerson(@PathVariable Long id){

        if(!deliveryPersonService.existsById(id)){
            throw new ResourceNotFoundException("No existe un repartidor con id " + id);
        }

        deliveryPersonService.deleteById(id);

        return ResponseEntity
                .noContent()
                .build();
    }



}
