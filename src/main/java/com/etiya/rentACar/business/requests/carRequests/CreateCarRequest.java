package com.etiya.rentACar.business.requests.carRequests;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
	
	@JsonIgnore
	private int id;
	
	@NotNull
	@Min(1)
	@Max(2000)
	private double dailyPrice;
	
	@NotNull
	@Length(min=2,max=50)
	private String description;
	
	@NotNull
	@Min(2015)
	private double modelYear;

	@Min(0)
	private double kilometer;
	
	@NotNull
	private int colorId;
	
	@NotNull
	private int brandId;
	
	private String carStates;
	
	@NotNull
	private int cityId;
}
