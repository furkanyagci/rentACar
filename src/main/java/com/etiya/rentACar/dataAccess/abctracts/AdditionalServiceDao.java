package com.etiya.rentACar.dataAccess.abctracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etiya.rentACar.entities.AdditionalService;

@Repository
public interface AdditionalServiceDao extends JpaRepository<AdditionalService, Integer>{
	boolean existsByNameIgnoreCase(String additionalServiceName);
}
