package com.johanx.tienda.services.impl;

import com.johanx.tienda.dao.DeliveryPersonRepository;
import com.johanx.tienda.model.DeliveryPerson;
import com.johanx.tienda.services.IDeliveryPerson;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * servicio con los métodos para acceder a los datos de los repartidores
 */
@Service
@Transactional
public class DeliveryPersonService implements IDeliveryPerson {


    @Autowired
    private DeliveryPersonRepository dao;

    @Override
    public List<DeliveryPerson> findAll() {
        return dao.findAll();
    }

    @Override
    public List<DeliveryPerson> findByNationality(String nationality) {
        return dao.findByNationality(nationality);
    }

    @Override
    public DeliveryPerson findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public List<DeliveryPerson> findByName(String name) {
        return dao.findByName(name);
    }


    @Override
    public boolean existsByName(String name) {
        return !dao.findByName(name).isEmpty();
    }

    @Override
    public boolean existsById(Long id) {
        return dao.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
      dao.deleteById(id);
    }

    @Override
    public DeliveryPerson save(DeliveryPerson deliveryPerson) {
        return dao.save(deliveryPerson);
    }

    @Override
    public List<DeliveryPerson> findByLastName(String lastname) {
        return dao.findByLastName(lastname);
    }

    @Override
    public boolean existsByLastName(String lastname) {
        return !dao.findByLastName(lastname).isEmpty();
    }

    @Override
    public boolean existsByNationality(String nationality) {
        return !dao.findByNationality(nationality).isEmpty();
    }

    @Override
    public boolean existsByAge(int age) {
        return !dao.findByAge(age).isEmpty();
    }


    /**
     * método para verificar que no existan repartidores iguales excluyendo el id. útil en
     * método post
     *
     * @return true si el repartidor ya existe
     */
    @Override
    public boolean existsByNameLastNameAgeAndNationality(DeliveryPerson deliveryPerson) {
        return existsByName(deliveryPerson.getName())
                && existsByLastName(deliveryPerson.getLastName())
                && existsByNationality(deliveryPerson.getNationality())
                && existsByAge(deliveryPerson.getAge());
    }


}
