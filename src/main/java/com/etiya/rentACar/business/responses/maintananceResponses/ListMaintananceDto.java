package com.etiya.rentACar.business.responses.maintananceResponses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListMaintananceDto {
	private int id;
	private LocalDate addedDate;
	private LocalDate returnedDate;
	private String description;
	private int carId;
	private String carBrandIdName;
}
