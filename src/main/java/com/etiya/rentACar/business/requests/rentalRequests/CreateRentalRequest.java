package com.etiya.rentACar.business.requests.rentalRequests;

import java.time.LocalDate;

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
	
	@JsonIgnore
	private double totalPrice;

	private int carId;
	
	private int customerId;
	
	private int rentCityId;
	
	private int returnedCityId;
}
