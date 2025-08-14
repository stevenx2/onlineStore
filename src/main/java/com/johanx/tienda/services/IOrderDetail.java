package com.johanx.tienda.services;

import com.johanx.tienda.model.OrderDetail;
import com.johanx.tienda.runtimeException.ResourceNotFoundException;

import java.util.List;

/**
 * firma de métodos para los servicios de la tabla de order_details
 */
public interface IOrderDetail {

    List<OrderDetail> findAll();

    OrderDetail findById(Long id);

    OrderDetail save(OrderDetail orderDetail) throws ResourceNotFoundException;

    void deleteById(Long id);

    boolean existsById(Long id);

}
