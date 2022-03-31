package com.etiya.rentACar.business.requests.maintananceRequests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMaintananceRequest {
		
		private int id;
		
		private LocalDate addedDate;
		
		private LocalDate returnedDate;
		
		private String description;
		
		private int carId;
		
}
