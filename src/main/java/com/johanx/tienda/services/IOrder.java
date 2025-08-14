package com.johanx.tienda.services;

import com.johanx.tienda.model.Order;

import java.util.List;

/**
 * firma de métodos para los servicios que acceden a la tabla de pedidos (Order)
 */
public interface IOrder {

    List<Order> findAll();

    Order findById(Long id);

    Order save(Order order);

    void deleteById(Long id);

    boolean existsById(Long id);

}
