package com.etiya.rentACar.business.requests.damageRequests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDamageRequest {
	//@JsonIgnore id bilgisini almadan update yapamayız o yüzden bunu sildik
	private int id;
	
	private LocalDate date;
	
	private String description;
	
	private int carId;
}
