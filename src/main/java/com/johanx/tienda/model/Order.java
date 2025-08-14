package com.johanx.tienda.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;


    @Column(name = "order_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;


    public Order(LocalDate date) {
        this.date = date;
    }
}
