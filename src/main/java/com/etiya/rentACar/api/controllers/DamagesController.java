package com.etiya.rentACar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACar.business.abstracts.DamageService;
import com.etiya.rentACar.business.requests.damageRequests.CreateDamageRequest;
import com.etiya.rentACar.business.requests.damageRequests.DeleteDamageRequest;
import com.etiya.rentACar.business.requests.damageRequests.UpdateDamageRequest;
import com.etiya.rentACar.business.responses.damageResponses.DamageDto;
import com.etiya.rentACar.business.responses.damageResponses.ListDamageDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

@RestController//
@RequestMapping("/api/damages")
public class DamagesController {
	private DamageService damageService;

	public DamagesController(DamageService damageService) {
		this.damageService = damageService;
	}
	
	@GetMapping("/getbyid")
	public DataResult<DamageDto> getById(@RequestParam("id") int id){
		return this.damageService.getById(id);
	}
	
	@GetMapping("/getall")
	public DataResult<List<ListDamageDto>> getAll(){
		return this.damageService.getAll();
	}
	
	@GetMapping("/getbycarid")
	public DataResult<List<ListDamageDto>>  getByCarId(@RequestParam("id") int id){
		return this.damageService.getByCarId(id);
	}
	
	@GetMapping("/getallpaged")
	public DataResult<List<ListDamageDto>> getAllPaged(int pageNo, int pageSize){
		return this.damageService.getAllPaged(pageNo, pageSize);
	}
	
	@GetMapping("/getallsorted")
	public DataResult<List<ListDamageDto>> getAllSorted(String sorted, String field){
		return this.damageService.getAllSorted(sorted, field);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateDamageRequest createDamageRequest) {
		return this.damageService.add(createDamageRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateDamageRequest updateDamageRequest) {
		return damageService.update(updateDamageRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteDamageRequest deleteDamageRequest) {
		return this.damageService.delete(deleteDamageRequest);
	}
}
