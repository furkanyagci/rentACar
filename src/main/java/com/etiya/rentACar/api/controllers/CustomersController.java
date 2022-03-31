package com.etiya.rentACar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACar.business.abstracts.CustomerService;
import com.etiya.rentACar.business.requests.customerRequests.CreateCustomerRequest;
import com.etiya.rentACar.business.requests.customerRequests.DeleteCustomerRequest;
import com.etiya.rentACar.business.requests.customerRequests.UpdateCustomerRequest;
import com.etiya.rentACar.business.responses.customerResponses.CustomerDto;
import com.etiya.rentACar.business.responses.customerResponses.ListCustomerDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/customers")
public class CustomersController {
	
	private CustomerService customerService;

	public CustomersController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<ListCustomerDto>> getAll(){
		return this.customerService.getAll();
	}
	
	@GetMapping("/getbyid")
	public DataResult<CustomerDto> getById(@RequestParam("CustomerId") int CustomerId) {
		return this.customerService.getById(CustomerId);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateCustomerRequest createCustomerRequest) throws Exception {
		return this.customerService.add(createCustomerRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateCustomerRequest updateCustomerRequest) {
		return customerService.update(updateCustomerRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteCustomerRequest deleteCustomerRequest) {
		return this.customerService.delete(deleteCustomerRequest);
	}
}
