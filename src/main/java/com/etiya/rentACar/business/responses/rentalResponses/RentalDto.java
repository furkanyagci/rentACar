package com.etiya.rentACar.business.responses.rentalResponses;

import java.time.LocalDate;

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
	private double totalPrice;
	private String carBrandName;
	private String customerFirstName;
	private String customerLastName;
	private String rentCityIdName;
	private String returnedCitiesName;
}
