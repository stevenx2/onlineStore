package com.johanx.tienda.dao;

import com.johanx.tienda.model.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson,Long>  {

    List<DeliveryPerson> findByName(String name);

    List<DeliveryPerson> findByNationality(String nationality);


    List<DeliveryPerson> findByLastName(String lastname);

    List<DeliveryPerson>  findByAge(int age);

}
