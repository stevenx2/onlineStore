package com.johanx.tienda.services.impl;

import com.johanx.tienda.dao.OrderRepository;
import com.johanx.tienda.model.Order;
import com.johanx.tienda.services.IOrder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/***
 * servicio para acceder y modificar registros de la tabla Order
 */
@Service
@Transactional
public class OrderService implements IOrder {


    @Autowired
    private OrderRepository dao;

    @Override
    public List<Order> findAll() {
        return dao.findAll();
    }

    @Override
    public Order findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public Order save(Order order) {
        return dao.save(order);
    }

    @Override
    public void deleteById(Long id) {
       dao.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return dao.existsById(id);
    }
}
