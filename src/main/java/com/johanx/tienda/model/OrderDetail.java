package com.johanx.tienda.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * entidad de los detalles de los pedidos
 */

@Entity
@Table(name = "order_details")
@Data
@NoArgsConstructor
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_detail_id;

    private int quantity;

    @Column(name = "sub_total")
    private double subTotal;

    @ManyToOne
    @JoinColumn(name = "delivery_person_id")
    private DeliveryPerson deliveryPerson;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


    public OrderDetail(int quantity, double subTotal, DeliveryPerson deliveryPerson, Product product) {
        this.quantity = quantity;
        this.subTotal = subTotal;
        this.deliveryPerson = deliveryPerson;
        this.product = product;
    }
}
