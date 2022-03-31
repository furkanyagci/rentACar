package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.requests.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.DeleteCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.entities.CarStates;

public interface CarService {
	DataResult<CarDto> getById(int id);
	
	DataResult<List<ListCarDto>> getAll();
	DataResult<List<ListCarDto>> getAllByModelYear(double modelYear);
	DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize);
	DataResult<List<ListCarDto>> getAllSorted();
	
	Result add(CreateCarRequest createCarRequest);
	Result delete(DeleteCarRequest deleteCarRequest);
	Result update(UpdateCarRequest updateCarRequest);
	void updateCarState(int carId, CarStates carStates);
	
	void checkIfCarStates(int carId);
	
	DataResult<List<ListCarDto>>getByCityId(int cityId);
}
