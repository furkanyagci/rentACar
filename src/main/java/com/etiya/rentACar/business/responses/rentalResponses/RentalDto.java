package com.etiya.rentACar.business.responses.rentalResponses;

import java.time.LocalDate;

import com.etiya.rentACar.entities.Car;
import com.etiya.rentACar.entities.City;
import com.etiya.rentACar.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto {
	private int id;
	private LocalDate rentDate;
	private LocalDate returnDate;
	private double dailyPrice;
	private double kilometer;
	//Aşağıdaki String çevir
	private Car carBrandName;
	private Customer customerFirstName;
	private Customer customerLastName;
	private City rentCityIdName;
	private City returnedCitiesName;
}
