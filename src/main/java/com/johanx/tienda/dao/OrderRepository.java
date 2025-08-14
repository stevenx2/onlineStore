package com.johanx.tienda.dao;

import com.johanx.tienda.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
