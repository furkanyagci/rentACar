package com.etiya.rentACar.dataAccess.abctracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etiya.rentACar.entities.Rental;

@Repository
public interface RentalDao extends JpaRepository<Rental,Integer>{

}
