package com.johanx.tienda.Controller;


import com.johanx.tienda.model.OrderDetail;
import com.johanx.tienda.runtimeException.ResourceAlreadyExists;
import com.johanx.tienda.runtimeException.ResourceNotFoundException;
import com.johanx.tienda.services.IOrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * clase con los endponts de la tabla order_details
 */

@RestController
@RequestMapping(value = "/orderDetails")
public class OrderDetailController {

    @Autowired
    private IOrderDetail orderDetailService;


    @GetMapping
    public ResponseEntity<List<OrderDetail>> getAll(){
        return ResponseEntity
                .ok(orderDetailService.findAll());
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDetail> getById(@PathVariable Long id){

        if(!orderDetailService.existsById(id)){
            throw new ResourceNotFoundException("No existe ningún detalle de orden con id " + id);
        }

        return ResponseEntity
                .ok(orderDetailService.findById(id));
    }



    @PostMapping
    public ResponseEntity<OrderDetail> saveOrderDetail(@RequestBody OrderDetail orderDetail){

        OrderDetail savedOrderDetail = orderDetailService.save(orderDetail);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedOrderDetail.getOrder_detail_id()).toUri();

        return ResponseEntity
                .created(uri)
                .body(savedOrderDetail);
    }




    @PutMapping(value = "/{id}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable Long id, @RequestBody OrderDetail orderDetail){

        if(!orderDetailService.existsById(id)){
            throw new ResourceNotFoundException("El detalle de orden que quiere actualizar con id " + id + " no existe");
        }

        orderDetail.setOrder_detail_id(id);

        return ResponseEntity
                .ok(orderDetailService.save(orderDetail));

    }



    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteOrderDetail(@PathVariable Long id){
        if(!orderDetailService.existsById(id)){
            throw new ResourceNotFoundException("El detalle de orden que quiere eliminar con id " + id + " no existe");
        }


        orderDetailService.deleteById(id);

        return ResponseEntity
                .noContent()
                .build();

    }






}
