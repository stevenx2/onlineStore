package com.johanx.tienda.Controller;

import com.johanx.tienda.model.Order;
import com.johanx.tienda.runtimeException.ResourceNotFoundException;
import com.johanx.tienda.services.IOrder;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {


    @Autowired
    IOrder orderService;


    @GetMapping
    public ResponseEntity<List<Order>> getAll(){
        return ResponseEntity
                .ok(orderService.findAll());
    }



    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id){

        if(!orderService.existsById(id)){
            throw new ResourceNotFoundException("No existe una orden con el id " + id);
        }

        return ResponseEntity
                .ok(orderService.findById(id));
    }



    @PostMapping
    public ResponseEntity<Order> saveOrder(@RequestBody Order order){
        Order savedOrder = orderService.save(order);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedOrder.getOrder_id()).toUri();

        return ResponseEntity
                .created(uri)
                .body(savedOrder);
    }



    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id){
        if(!orderService.existsById(id)){
            throw new ResourceNotFoundException("La orden que quiere eliminar con id " + id + " no existe");
        }

        orderService.deleteById(id);

        return ResponseEntity
                .noContent()
                .build();
    }



    @PutMapping(value = "/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id,@RequestBody Order order){
        if(!orderService.existsById(id)){
            throw new ResourceNotFoundException("La orden que quiere actualizar con id " + id + " no existe");
        }

        order.setOrder_id(id);

        return ResponseEntity
                .ok(orderService.save(order));
    }
}
