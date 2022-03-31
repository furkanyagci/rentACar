package com.etiya.rentACar.dataAccess.abctracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etiya.rentACar.entities.Maintanance;

@Repository
public interface MaintananceDao extends JpaRepository<Maintanance, Integer>{
	//boolean existsByCarIdAndReturnedDateIsNull(int id);
	List<Maintanance> getByCarId(int carId);
}
