package com.etiya.rentACar.business.requests.rentalRequests;

import java.time.LocalDate;

import com.etiya.rentACar.entities.Car;
import com.etiya.rentACar.entities.City;
import com.etiya.rentACar.entities.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {

	private LocalDate rentDate;

	private LocalDate returnDate;
	
	@JsonIgnore
	private double dailyPrice;

	private double kilometer;

	//aşağıdakiler int çevir
	private Car carId;
	
	private int customerId;
	
	private City rentCityId;
	
	private City returnedCityId;

}
