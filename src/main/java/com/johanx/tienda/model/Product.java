package com.johanx.tienda.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * entidad de los productos
 */
@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;


    @Column(unique = true)
    private String name;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;


    public Product(String name, Double price,Supplier supplier,Category category) {
        this.name = name;
        this.price = price;
        this.supplier = supplier;
        this.category = category;
    }
}
