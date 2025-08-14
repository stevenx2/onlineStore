package com.johanx.tienda.services.impl;


import com.johanx.tienda.dao.DeliveryPersonRepository;
import com.johanx.tienda.dao.OrderDetailReposity;
import com.johanx.tienda.dao.OrderRepository;
import com.johanx.tienda.dao.ProductRepository;
import com.johanx.tienda.model.OrderDetail;
import com.johanx.tienda.model.Product;
import com.johanx.tienda.runtimeException.ResourceNotFoundException;
import com.johanx.tienda.services.IDeliveryPerson;
import com.johanx.tienda.services.IOrderDetail;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * servicio con método para acceder y modificar la tabla order_details
 */

@Service
@Transactional
public class OrderDetailService implements IOrderDetail {


    @Autowired
    private OrderDetailReposity dao;

    @Autowired
    private DeliveryPersonRepository deliveryPersonDao;

    @Autowired
    private ProductRepository productDao;

    @Autowired
    private OrderRepository orderDao;




    @Override
    public List<OrderDetail> findAll() {
        return dao.findAll();
    }

    @Override
    public OrderDetail findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) throws ResourceNotFoundException {

        Long productId = orderDetail.getProduct().getProduct_id();
        Long deliveryPersonId = orderDetail.getDeliveryPerson().getDelivery_person_id();
        Long orderId = orderDetail.getOrder().getOrder_id();


        if(!productDao.existsById(productId)){
            throw new ResourceNotFoundException("el id " + productId + " no esta relacionado con ningún producto, Favor especificar un identificador existente");
        }

        if(!deliveryPersonDao.existsById(deliveryPersonId)){
            throw new ResourceNotFoundException("el id " + deliveryPersonId + " no esta relacionado con ningún repartidor, Favor especificar un identificador existente");
        }

        if(!orderDao.existsById(orderId)){
            throw new ResourceNotFoundException("el id " + orderId + " no esta relacionado con ninguna orden, Favor especificar un identificador existente");
        }

        orderDetail.setProduct(productDao.findById(productId).orElse(null));
        orderDetail.setDeliveryPerson(deliveryPersonDao.findById(deliveryPersonId).orElse(null));
        orderDetail.setOrder(orderDao.findById(orderId).orElse(null));

        return dao.save(orderDetail);
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
