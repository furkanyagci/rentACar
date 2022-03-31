package com.etiya.rentACar.business.responses.damageResponses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DamageDto {
	
	private int id;
	
	private LocalDate date;
	
	private String description;
	
	private int carId;
	
	private String carBrandIdName; 

}