package com.johanx.tienda.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * entidad del repartidor
 */

@Entity
@Table(name = "delivery_person")
@Data
@NoArgsConstructor
public class DeliveryPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long delivery_person_id;


    private String name;

    @Column(name = "last_name")
    private String lastName;

    private int age;

    private String nationality;


    public DeliveryPerson(String name, String lastName, int age, String nationality) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.nationality = nationality;
    }
}
