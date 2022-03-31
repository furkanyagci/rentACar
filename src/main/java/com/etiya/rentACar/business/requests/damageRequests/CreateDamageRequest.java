package com.etiya.rentACar.business.requests.damageRequests;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDamageRequest {
	
	@JsonIgnore/*Bunu yazınca frontend bunu göremez ve veri atama yapamaz. ekleme işlemi yaparken Dbde var olan kayıt güncelleniyorsa
	bunu yazarsan çözer. */
	private int id;
	
	private LocalDate date;
	
	private String description;
	
	private int carId;
}
