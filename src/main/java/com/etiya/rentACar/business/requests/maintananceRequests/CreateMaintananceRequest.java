package com.etiya.rentACar.business.requests.maintananceRequests;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMaintananceRequest {
	
	@JsonIgnore
	private int id;
	/*private int carId; Buradaki sıralama ile Maintanance class'ındaki sıralama aynı olmalı.CarId buradaydı aşağı 
	 * alınca hata düzeldi.*/
	private LocalDate addedDate;
	
	private LocalDate returnedDate;
	
	private String description;
	
	private int carId;
	
}
