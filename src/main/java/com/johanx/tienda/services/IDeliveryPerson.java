package com.johanx.tienda.services;

import com.johanx.tienda.model.DeliveryPerson;

import java.util.List;

/**
 * firma de métodos para el servicio de repartidores
 */
public interface IDeliveryPerson {

    List<DeliveryPerson> findAll();

    List<DeliveryPerson> findByNationality(String nationality);

    DeliveryPerson findById(Long id);

    List<DeliveryPerson> findByName(String name);


    boolean existsByName(String name);

    boolean existsById(Long id);


    void deleteById(Long id);


    DeliveryPerson save(DeliveryPerson deliveryPerson);

    List<DeliveryPerson> findByLastName(String lastname);

    boolean existsByLastName(String lastname);


    boolean existsByNationality(String nationality);


    boolean existsByAge(int age);

    /**
     * firma para verificar que no existan repartidores iguales excluyendo el id. útil en
     * método post
     *  @return  true si el repartidor ya existe
     */
    boolean existsByNameLastNameAgeAndNationality(DeliveryPerson deliveryPerson);
}
