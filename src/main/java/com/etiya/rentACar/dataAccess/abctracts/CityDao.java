package com.etiya.rentACar.dataAccess.abctracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etiya.rentACar.entities.City;

@Repository
public interface CityDao extends JpaRepository<City, Integer>{
	boolean existsByNameIgnoreCase(String CityName);
}
