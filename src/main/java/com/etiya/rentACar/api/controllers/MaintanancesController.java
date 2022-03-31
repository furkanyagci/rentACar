package com.etiya.rentACar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACar.business.abstracts.MaintananceService;
import com.etiya.rentACar.business.requests.maintananceRequests.CreateMaintananceRequest;
import com.etiya.rentACar.business.requests.maintananceRequests.DeleteMaintananceRequest;
import com.etiya.rentACar.business.requests.maintananceRequests.UpdateMaintananceRequest;
import com.etiya.rentACar.business.responses.maintananceResponses.ListMaintananceDto;
import com.etiya.rentACar.business.responses.maintananceResponses.MaintananceDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

@RestController//
@RequestMapping("/api/maintanances")
public class MaintanancesController {
	
	private MaintananceService maintananceService;

	public MaintanancesController(MaintananceService maintananceService) {
		this.maintananceService = maintananceService;
	}
	
	@GetMapping("/getbyid")
	public DataResult<MaintananceDto> getById(@RequestParam("id") int id) {
		return maintananceService.getById(id);
	}
	
	@GetMapping("/getall")
	public DataResult<List<ListMaintananceDto>> getAll() {
		return maintananceService.getAll();
	}
	
	@GetMapping("/getbycarid")
	public DataResult<List<ListMaintananceDto>> getByCarId(@RequestParam("carId") int carId) {
		return maintananceService.getByCarId(carId);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateMaintananceRequest createMaintananceRequest) {
		return maintananceService.add(createMaintananceRequest);
	}

	@PostMapping("/update")
	public Result update(@RequestBody UpdateMaintananceRequest updateMaintananceRequest) {
		return maintananceService.update(updateMaintananceRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteMaintananceRequest deleteMaintananceRequest) {
		return maintananceService.delete(deleteMaintananceRequest);
	}
}
