package com.etiya.rentACar.business.requests.customerRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {
	private String nationalityNumber;
	private String firstName;
	private String lastName;
	private String phoneNumber;
}
