package com.etiya.rentACar.business.responses.customerResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
	private int id;
	private String nationalityNumber;
	private String firstName;
	private String lastName;
	private String phoneNumber;
}
