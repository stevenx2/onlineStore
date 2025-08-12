package com.johanx.tienda.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * entidad de las empresas proveedoras
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "suppliers")
public class Supplier {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplier_id;

    private String location;

    private String name;


    public Supplier(String location, String name) {
        this.location = location;
        this.name = name;
    }
}
