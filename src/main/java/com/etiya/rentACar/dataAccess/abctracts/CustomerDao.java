package com.etiya.rentACar.dataAccess.abctracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etiya.rentACar.entities.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer,Integer>{
	boolean existsByNationalityNumber(String NationalityNumber);
}
