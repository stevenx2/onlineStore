package com.johanx.tienda.dao;

import com.johanx.tienda.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailReposity extends JpaRepository<OrderDetail,Long> {

    List<OrderDetail> findByQuantity(int quantity);

    List<OrderDetail> findBySubTotal(double subtotal);

}
