package com.etiya.rentACar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.requests.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.DeleteCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

@RestController//
@RequestMapping("/api/cars")
public class CarsController {

	private CarService carService;

	public CarsController(CarService carService) {
		this.carService = carService;
	}
	
	@GetMapping("/getbyid")
	DataResult<CarDto> getById(@RequestParam("carId") int carId) {
		return this.carService.getById(carId);
	}
	
	@GetMapping("/getbycityid")
	DataResult<List<ListCarDto>> getCityId(@RequestParam("cityId") int cityId) {
		return this.carService.getByCityId(cityId);
	}
	
	@GetMapping("/getall")
	public DataResult<List<ListCarDto>> getAll(){
		return this.carService.getAll();
	}
	
	@GetMapping("/getallbymodelyear")
	public DataResult<List<ListCarDto>> getAllByModelYear(@RequestParam("modelYear") double modelYear){//GetMapping de parametreyi bu şekilde alırız
		return this.carService.getAllByModelYear(modelYear);
	}
	
	@GetMapping("/getallpaged")
	public DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize){
		return this.carService.getAllPaged(pageNo, pageSize);
	}
	
	@GetMapping("/getallsorted")
	public DataResult<List<ListCarDto>> getAllSorted(){
		return this.carService.getAllSorted();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) {//@Valid
		return this.carService.add(createCarRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateCarRequest updateCarRequest) {
		return carService.update(updateCarRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteCarRequest deleteCarRequest) {
		return this.carService.delete(deleteCarRequest);
	}
}