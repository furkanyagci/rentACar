package com.etiya.rentACar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACar.business.abstracts.CityService;
import com.etiya.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.etiya.rentACar.business.requests.cityRequests.DeleteCityRequest;
import com.etiya.rentACar.business.requests.cityRequests.UpdateCityRequest;
import com.etiya.rentACar.business.responses.cityResponses.CityDto;
import com.etiya.rentACar.business.responses.cityResponses.ListCityDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

@RestController//
@RequestMapping("/api/cities")
public class CitiesController {
	
	private CityService cityService;

	public CitiesController(CityService cityService) {
		this.cityService = cityService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<ListCityDto>> getAll(){
		return this.cityService.getAll();
	}
	
	@GetMapping("/getbyid")
	public DataResult<CityDto> getById(@RequestParam("cityId") int cityId) {
		return this.cityService.getById(cityId);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateCityRequest createCityRequest) {
		return this.cityService.add(createCityRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateCityRequest updateCityRequest) {
		return cityService.update(updateCityRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteCityRequest deleteCityRequest) {
		return this.cityService.delete(deleteCityRequest);
	}
}
